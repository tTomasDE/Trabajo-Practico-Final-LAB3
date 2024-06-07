package Modelo;

import Modelo.Excepciones.eEmpleadoNoEncontrado;
import Modelo.Excepciones.eRopaNoEncontrada;
import Modelo.Excepciones.eSinStock;
import Modelo.Finanzas.Caja;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Cliente;
import Modelo.Humanos.Empleado;
import Modelo.Interfaces.Exportable;
import Modelo.Interfaces.FileManager;
import Modelo.Interfaces.JsonUtiles;
import Modelo.Mercaderia.Ropa;
import Modelo.Mercaderia.Talle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Local implements Serializable, Exportable , FileManager {
    private static final long serialVersionUID = -862208884450743488L;
    private String nombre;
    private String telefono;
    private String direccion;
    private int altura;
    private String horarios;
    private Caja caja;
    private HashSet<Ropa> stockRopa;
    private HashSet<Empleado> empleados;
    private HashSet<Cliente> clientes;

    public Local() {
        this.nombre = "";
        this.telefono = "";
        this.direccion = "";
        this.altura = 0;
        this.horarios = "";
        this.caja=new Caja();
        this.stockRopa = new HashSet<>();
        this.empleados = new HashSet<>();
        this.clientes = new HashSet<>();
    }
    public Local(String nombre, String telefono, String direccion, int altura, String horarios) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.altura = altura;
        this.horarios = horarios;
        this.caja=new Caja();
        this.stockRopa = new HashSet<>();
        this.empleados = new HashSet<>();
        this.clientes = new HashSet<>();
    }

///////////////////////////GETTERS Y SETTERS///////////////////////////////////////////////
    public String getDireccion() {
        return direccion;
    }
    public int getAltura() {
        return altura;
    }
    public String getHorarios() {
        return horarios;
    }
    public HashSet<Ropa> getStockRopa() {
        return stockRopa;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }
    public HashSet<Empleado> getEmpleados() {
        return empleados;
    }
    public HashSet<Cliente> getClientes() {
        return clientes;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    //////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////METODOS////////////////////////////////////////////////////

    public String imprimirInformacionDelLocal() {
        return "Nombre del Local: " + getNombre() + "\n" + "Contacto: " + getTelefono() + "\n" + "Dirección: " + getDireccion() + " " + getAltura() + "\n" + "Horarios: " + getHorarios() + "\n";
    }
    private int obtenerUltimoIdEmpleado() {
        int maxId = 0;
        for (Empleado emp : empleados) {
            if (emp.getId() > maxId) {
                maxId = emp.getId();
            }
        }
        return maxId;
    }
    public void agregarEmpleado(Empleado e){
        e.setId(obtenerUltimoIdEmpleado()+1);
        this.empleados.add(e);
    }
    public String imprimirEmpleados(){
        String info="";

        for(Empleado emp : this.empleados){
            if (emp.isDisponible()){
                info+= emp.toString()+"\n";
            }
        }
        return info;
    }
    public String imprimirEmpleadosDadosDeBaja(){
        String info="";

        for(Empleado emp : this.empleados){
            if (!emp.isDisponible()){
                info+= emp.toString()+"\n";
            }
        }
        return info;
    }
    private int obtenerUltimoIdCliente() {
        int maxId = 0;
        for (Cliente cli : clientes) {
            if (cli.getId() > maxId) {
                maxId = cli.getId();
            }
        }
        return maxId;
    }
    public void agregarCliente(Cliente c){
        c.setId(obtenerUltimoIdCliente()+1);
        this.clientes.add(c);
    }
    private int obtenerUltimoIdRopa() {
        int maxId = 0;
        for (Ropa ropa : this.stockRopa) {
            if (ropa.getId() > maxId) {
                maxId = ropa.getId();
            }
        }
        return maxId;
    }
    public boolean buscarIdPorRopa (int ID){
        boolean rta=false;
        for(Ropa ropa : this.stockRopa){
            if(ropa.getId()==ID){
                rta=true;
            }
        }
        return rta;
    }
    public Ropa buscarRopaPorId(int id) {
        Ropa ropaEncontrada = null;

        for (Ropa rop : stockRopa) {
            if (rop.getId() == id) {
                ropaEncontrada = rop;
            }
        }

        return ropaEncontrada;
    }
    public void agregarRopaAlStock(Ropa r) {
        boolean encontrada = false;
        for (Ropa ropaExistente : stockRopa) {
            if (ropaExistente.equals(r)) {
                ropaExistente.setStock(ropaExistente.getStock() + r.getStock());
                encontrada = true;
            }
        }
        if (!encontrada) {
            if (r.getId() == 0) {
                r.setId(obtenerUltimoIdRopa() + 1);
            }
            stockRopa.add(r);
        }
    }
    public String mostrarStockRopa() {
        String info="";
        int i=0;
        for(Ropa ro : this.stockRopa){
            if(ro.isDisponibilidad()){
            info+=ro.toString();
        }
        }
        return info;
    }
    public String mostrarStockRopaNoDisponible() {
        String info="";
        int i=0;
        for(Ropa ro : this.stockRopa){
            if(!ro.isDisponibilidad()){
                info+=ro.toString();
            }
        }
        return info;
    }
    public void devolverRopaAlStock(int idPrenda) {
        for (Ropa prenda : stockRopa) {
            if (prenda.getId() == idPrenda) {
                    prenda.setStock(prenda.getStock() + 1);
            }
        }
    }
    public void agregarRecaudacion(double total){
        this.caja.agregarRecaudacion(total);

    }
    public void retirarDinero(double retirar){
        this.caja.retirarDinero(retirar);
    }
    public String imprimirRetiros(){
        return this.caja.obtenerRetirosPorFecha();
    }
    public double getRecaudacion(){
        return this.caja.getRecaudacion();
    }
    public String manejarDarDeBajaEmpleado(int ID) {
        try {
            darDeBajaEmpleado(ID);
            return "Empleado dado de baja con éxito: " + ID;
        } catch (eEmpleadoNoEncontrado e) {
            return e.getMessage();
        }
    }
    public void darDeBajaEmpleado(int ID) throws eEmpleadoNoEncontrado {
        boolean empleadoEncontrado = false;
        for(Empleado emp : this.empleados) {
            if(emp.isDisponible() && emp.getId() == ID) {
                emp.setDisponible(false);
                empleadoEncontrado = true;
                break;
            }
        }
        if (!empleadoEncontrado) {
            throw new eEmpleadoNoEncontrado("No se encontró ningún empleado con el ID especificado.");
        }
    }
    public void darDeAltaEmpleado(int ID) throws eEmpleadoNoEncontrado {
        boolean empleadoEncontrado = false;
        for(Empleado emp : this.empleados) {
            if(!emp.isDisponible() && emp.getId() == ID) {
                emp.setDisponible(true);
                empleadoEncontrado = true;
                break;
            }
        }
        if (!empleadoEncontrado) {
            throw new eEmpleadoNoEncontrado("No se encontró ningún empleado con el ID especificado.");
        }
    }
    public String manejarDarDeAltaEmpleado(int ID) {
        try {
            darDeAltaEmpleado(ID);
            return "Empleado dado de alta con éxito: " + ID;
        } catch (eEmpleadoNoEncontrado e) {
            return e.getMessage();
        }
    }
    public void editarNombreCompletoEmpleado(int ID,String nombre, String Apellido){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && ID==emp.getId()){
                emp.setNombre(nombre);
                emp.setApellido(Apellido);
            }
        }
    }
    public void editarSalarioEmpleado(int ID,double salario){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && ID==emp.getId()){
                emp.setSalario(salario);

            }
        }
    }
    public void editarHorariosEmpleado(int ID,String Horario){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && ID==emp.getId()){
                emp.setHorarios(Horario);

            }
        }
    }
    public void editarDNIEmpleado(int ID,String DNI){
        for(Empleado emp : this.empleados){
            if(emp.isDisponible() && ID==emp.getId()){
                emp.setDni(DNI);

            }
        }
    }
    public boolean hayEmpleadosDadosDeBaja() {
        boolean hayDadosDeBaja = false;
        for (Empleado empleado : empleados) {
            if (!empleado.isDisponible()) {
                hayDadosDeBaja = true;
            }
        }
        return hayDadosDeBaja;
    }
    public boolean hayRopaDadaDeBaja() {
        boolean hayDadosDeBaja = false;
        for (Ropa ropa : this.stockRopa) {
            if (!ropa.isDisponibilidad()) {
                hayDadosDeBaja = true;
            }
        }
        return hayDadosDeBaja;
    }
    public boolean buscarIdEmpleado (int ID){
        boolean rta=false;
        for(Empleado emp : this.empleados){
            if(emp.getId()==ID){
                rta=true;
            }
        }
        return rta;
    }
    public Cliente buscarClientePorDni(String DNI){
        Cliente cliente=null;
        for (Cliente cli : this.clientes){
            if(cli.getDni().equals(DNI)){
                cliente=cli;
            }
        }
        return cliente;
    }
    public String imprimirClientes(){
        String info="";
        for(Cliente cli : this.clientes){
            info+=cli.toString()+"\n";
        }
        return info;
    }
    public void cambiarPrecioRopa(int id, double nuevoPrecio) {
        for (Ropa ropa : this.stockRopa) {
            if (ropa.getId() == id) {
                ropa.setPrecio(nuevoPrecio);
            }
        }
    }
    public void cambiarStockRopa(int idPrenda, int nuevoStock) {
        for (Ropa ropa : this.stockRopa) {
            if (ropa.getId() == idPrenda) {
                ropa.setStock(nuevoStock);
            }
        }
    }
    public String filtrarRopaPorTipo(String tipo){
        String info="";

        for(Ropa ro : this.stockRopa){
            if(ro.getTipo().equalsIgnoreCase(tipo)) {
                info += ro.toStringParaListaDeCompra()+"\n";

            }
        }
        return info;
    }
    public String filtrarRopaPorTalle(Talle talle){
        String info="";

        for(Ropa ro : this.stockRopa){
            if(ro.getTalle().equals(talle)) {
                info += ro.toStringParaListaDeCompra()+"\n";

            }
        }
        return info;
    }
    public String filtrarRopaPorColor(String color){
        String info="";

        for(Ropa ro : this.stockRopa){
            if(ro.getColorRopa().equalsIgnoreCase(color)) {
                info += ro.toStringParaListaDeCompra()+"\n";

            }
        }
        return info;
    }
    public void darDeBajaRopa(int ID) throws eRopaNoEncontrada {
        boolean encontrada = false;
        for (Ropa ropa : this.stockRopa) {
            if (ropa.isDisponibilidad() && ropa.getId() == ID) {
                ropa.setDisponibilidad(false);
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            throw new eRopaNoEncontrada("La prenda con ID: " + ID + " no se encuentra en el stock o ya está dada de baja.");
        }
    }
    public String manejarDarDeBajaRopa(int ID) {
        try {
            darDeBajaRopa(ID);
            return "Ropa dada de baja con éxito: " + ID;
        } catch (eRopaNoEncontrada e) {
            return e.getMessage();
        }
    }
    public void darDeAltaRopa(int ID) throws eRopaNoEncontrada {
        boolean encontrada = false;
        for (Ropa ropa : this.stockRopa) {
            if (!ropa.isDisponibilidad() && ropa.getId() == ID) {
                ropa.setDisponibilidad(true);
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            throw new eRopaNoEncontrada("La prenda con ID: " + ID + " no se encuentra en el stock o ya está dada de alta.");
        }
    }
    public String manejarDarDeAltaRopa(int ID) {
        try {
            darDeAltaRopa(ID);
            return "Ropa dada de alta con éxito: " + ID;
        } catch (eRopaNoEncontrada e) {
            return e.getMessage();
        }
    }
    public void limpiarClientes(){
        this.clientes.clear();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////MANEJO ARCHIVOS////////////////////////////////////////////////////

    public void AgregarLocalAlArchivo(){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Informacion_Local.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);


                objectOutputStream.writeObject(this);


        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public Local ObtenerLocalDelArchivo(){
        Local lo = null;
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Informacion_Local.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            lo = (Local) objectInputStream.readObject();

            if (lo == null) {
                lo.stockRopa = new HashSet<>();
                lo.empleados = new HashSet<>();
                lo.clientes = new HashSet<>();
            }

        } catch (EOFException ex) {

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {


        } catch (ClassNotFoundException e) {

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ex) {

            }
        }
        return lo;
    }
    public void AgregarRopaAlArchivo (){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Stock_De_Ropa.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Ropa ro : this.stockRopa) {
                objectOutputStream.writeObject(ro);
            }

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void ObtenerRopaDelArchivo (){
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Stock_De_Ropa.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            this.stockRopa.clear();

            while (true) {
                Ropa ropa = (Ropa) objectInputStream.readObject();
                this.stockRopa.add(ropa);

            }


        } catch (EOFException ex)
        {

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                objectInputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void AgregarEmpleadosAlArchivo (){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Registro_Empleados.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Empleado emp : this.empleados) {
                objectOutputStream.writeObject(emp);
            }

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void ObtenerEmpleadosDelArchivo (){
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Registro_Empleados.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            this.empleados.clear();

            while (true) {
                Empleado emp = (Empleado) objectInputStream.readObject();
                this.empleados.add(emp);
            }

        } catch (EOFException ex) {

        } catch (FileNotFoundException ex) {

            System.out.println("El archivo de empleados no existe.");
        } catch (IOException exception) {

            exception.printStackTrace();
        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        } finally {
            try {

                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }
    public void AgregarClientesAlArchivo (){
        ObjectOutputStream objectOutputStream = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Registro_Clientes.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Cliente cli : this.clientes) {
                objectOutputStream.writeObject(cli);
            }

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {

            }

        }
    }
    public void ObtenerClientesDelArchivo() {
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("Registro_Clientes.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            this.clientes.clear();

            while (true) {
                Cliente cliente = (Cliente) objectInputStream.readObject();
                this.clientes.add(cliente);
            }
        } catch (EOFException ex) {

        } catch (FileNotFoundException ex) {

            try {
                FileOutputStream fileOutputStream = new FileOutputStream("Registro_Clientes.dat");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void exportarStockRopa() {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Ropa ro : this.stockRopa) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", ro.getId());
                jsonObject.put("tipo", ro.getTipo());
                jsonObject.put("talle", ro.getTalle().toString());
                jsonObject.put("color", ro.getColorRopa());
                jsonObject.put("precio", ro.getPrecio());
                jsonObject.put("stock", ro.getStock());
                jsonObject.put("disponibilidad", ro.isDisponibilidad());
                jsonArray.put(jsonObject);
            }
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        JsonUtiles.grabar(jsonArray, "Stock_Ropa_Exportada");
    }
    public void exportarClientes() {
        JSONArray jsonArrayClientes = new JSONArray();
        for (Cliente cliente : clientes) {
            JSONObject jsonObjectCliente = new JSONObject();
            try {
                jsonObjectCliente.put("dni", cliente.getDni());
                jsonObjectCliente.put("apellido", cliente.getApellido());
                jsonObjectCliente.put("nombre", cliente.getNombre());

                JSONArray jsonArrayCompras = new JSONArray();
                for (Compra compra : cliente.getHistorialCompras().getLista()) {
                    JSONObject jsonObjectCompra = new JSONObject();
                    jsonObjectCompra.put("fechaDeCompra", compra.getFechaDeCompra());

                    JSONObject itemsCompradosObject = new JSONObject();
                    for (Map.Entry<Ropa, Integer> entry : compra.getItemsComprados().entrySet()) {
                        Ropa ropa = entry.getKey();
                        JSONObject ropaObject = new JSONObject();
                        ropaObject.put("tipo", ropa.getTipo());
                        ropaObject.put("precio", ropa.getPrecio());
                        ropaObject.put("color", ropa.getColorRopa());
                        ropaObject.put("talle", ropa.getTalle().toString());

                        itemsCompradosObject.put(String.valueOf(ropa.getId()), ropaObject);
                        itemsCompradosObject.put(ropa.getId() + "_cantidad", entry.getValue());
                    }
                    jsonObjectCompra.put("itemsComprados", itemsCompradosObject);

                    jsonArrayCompras.put(jsonObjectCompra);
                }
                jsonObjectCliente.put("historialCompras", jsonArrayCompras);

                jsonArrayClientes.put(jsonObjectCliente);
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        JsonUtiles.grabar(jsonArrayClientes, "Clientes_Exportados");
    }
    public void exportarEmpleados() {
        JSONArray jsonArrayEmpleados = new JSONArray();
        for (Empleado empleado : empleados) {
            JSONObject jsonObjectEmpleado = new JSONObject();
            try {
                jsonObjectEmpleado.put("id", empleado.getId());
                jsonObjectEmpleado.put("nombre", empleado.getNombre());
                jsonObjectEmpleado.put("apellido", empleado.getApellido());
                jsonObjectEmpleado.put("horario", empleado.getHorarios());
                jsonObjectEmpleado.put("salario", empleado.getSalario());
                jsonObjectEmpleado.put("dni", empleado.getDni());
                jsonObjectEmpleado.put("estado", empleado.isDisponible() ? "Activo" : "Inactivo");

                jsonArrayEmpleados.put(jsonObjectEmpleado);
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        JsonUtiles.grabar(jsonArrayEmpleados, "Empleados_Exportados");
    }

}
