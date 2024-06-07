import Modelo.Finanzas.Caja;
import Modelo.Humanos.Cliente;
import Modelo.Finanzas.Compra;
import Modelo.Humanos.Empleado;
import Modelo.Local;
import Modelo.Mercaderia.Ropa;
import Modelo.Mercaderia.Talle;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Local local = new Local();
        local = local.ObtenerLocalDelArchivo();

        if (local == null) {

            local=ingresarInformacionInicial();

        }
        local.AgregarLocalAlArchivo();

        menu(local);


}
    public static Local ingresarInformacionInicial() {

        System.out.println("\nBienvenido! Parece que es la primera vez que ejecutas el programa.");
        System.out.println("\nPor favor, ingresa la información del local.\n");

        System.out.print("Nombre del Local: ");
        String nombre = validarString();

        System.out.print("Teléfono del Local: ");
        String telefono = validarTelefono();

        System.out.print("Dirección: ");
        String direccion = validarString();

        System.out.print("Altura: ");
        int altura = validarEntero();

        System.out.print("Horarios: ");
        String horarios = validarHorario();

        Local local = new Local(nombre, telefono, direccion, altura, horarios);

        return local;
    }
    public static void menu(Local local){

    boolean salir=false;

    while(!salir) {
        System.out.println("\n---------------------------------------------------\n");
        System.out.println("\n- Menú Principal: \n");
        System.out.println("[1] Gestionar Local\n");
        System.out.println("[2] Realizar Compra\n");
        System.out.println("[3] Salir\n");
        System.out.print("Ingrese su opción: ");

        int opcion = validarEntero();
        System.out.println("\n");
        switch (opcion) {
            case 1:
                subMenuGestionDelLocal(local);
                break;
            case 2:
                menuRealizarCompra(local);
                break;
            case 3:
                salir=true;
                System.out.println("\nSaliendo del programa....\n");
                break;
            default:
                System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                System.out.println("\n\n\n\n");
                break;
        }
    }
    }
    public static void subMenuGestionDelLocal (Local local){
        boolean salir=false;

        while(!salir){
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n-- Gestion del Local: \n");
            System.out.println("[1] Gestionar la informacion del Local\n");
            System.out.println("[2] Gestionar el Stock de Ropa\n");
            System.out.println("[3] Gestionar la Caja\n");
            System.out.println("[4] Gestionar Empleados\n");
            System.out.println("[5] Gestionar el Historial de Clientes del Local\n");
            System.out.println("[6] Volver al Menu Principal\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();
            System.out.println("\n");
            switch (opcion){
            case 1:
                subMenuInformacionDelLocal(local);
                break;
            case 2:
                subMenuGestionStockLocal(local);
                break;
            case 3:
                subMenuGestionDeCaja(local);
                break;
            case 4:
                subMenuGestionEmpleados(local);
                break;
            case 5:
                subMenuGestionClientesLocal (local);
                break;
            case 6:
                salir=true;
                break;
            default:
                System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                System.out.println("\n\n\n\n");
                break;
        }
        }
    }
    public static void subMenuInformacionDelLocal(Local local) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n--- Informacion del Local: \n");

            System.out.println("[1] Ver la informacion del Local\n");
            System.out.println("[2] Editar la informacion del Local\n");
            System.out.println("[3] Volver al Menu de Gestion del Local\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();
            System.out.println("\n");
            switch (opcion) {
                case 1:
                    local=local.ObtenerLocalDelArchivo();
                    System.out.println(local.imprimirInformacionDelLocal());
                    break;
                case 2:
                    boolean salirAux = false;
                    while (!salirAux) {
                        System.out.println("\n---------------------------------------------------\n");
                        System.out.println("\n---- Editar Informacion del Local: \n");
                        System.out.println("[1] Editar el Nombre\n");
                        System.out.println("[2] Editar el Teléfono\n");
                        System.out.println("[3] Editar la Direccion\n");
                        System.out.println("[4] Editar el Horario\n");
                        System.out.println("[5] Volver al Menu de Informacion del Local\n");
                        System.out.print("Ingrese su opción: ");
                        int opcionAux = validarEntero();
                        switch (opcionAux) {
                            case 1:
                                System.out.println("El nombre actual es: " + local.getNombre() + ", ¿Por cuál desea cambiarlo?\n");
                                System.out.println("Nuevo Nombre: ");
                                String nuevoNombre = validarString();
                                local.setNombre(nuevoNombre);
                                local.AgregarLocalAlArchivo();
                                break;
                            case 2:
                                System.out.println("El teléfono actual es: " + local.getTelefono() + ", ¿Por cuál desea cambiarlo?\n");
                                System.out.println("Nuevo Teléfono: ");
                                String nuevoTelefono = validarTelefono();
                                local.setTelefono(nuevoTelefono);
                                local.AgregarLocalAlArchivo();
                                break;
                            case 3:
                                System.out.println("La dirección actual es: " + local.getDireccion() + " " + local.getAltura() + ", ¿Por cuál desea cambiarla?\n");
                                System.out.println("Calle: ");
                                String nuevaDire = validarString();
                                System.out.println("Altura: ");
                                int nuevaAltu = validarEntero();
                                local.setDireccion(nuevaDire);
                                local.setAltura(nuevaAltu);
                                local.AgregarLocalAlArchivo();
                                break;
                            case 4:
                                System.out.println("El horario actual es: " + local.getHorarios() + ", ¿Por cuál desea cambiarlo?\n");
                                System.out.println("Nuevo Horario: ");
                                String nuevoHorario = validarHorario();
                                local.setHorarios(nuevoHorario);
                                local.AgregarLocalAlArchivo();
                                break;
                            case 5:
                                local.AgregarLocalAlArchivo();
                                salirAux = true;
                                break;
                            default:
                                System.out.println("\nOpción no válida. Por favor, ingrese de nuevo la opción que desea\n");
                                System.out.println("\n\n\n\n");
                                break;
                        }
                    }
                    break;

                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                    System.out.println("\n\n\n\n");
                    break;
            }
        }
    }
    public static void subMenuGestionDeCaja(Local local){
        boolean salir = false;

        while (!salir) {
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n--- Gestion de la Caja: \n");
            System.out.println("[1] Ver Recaudacion \n");
            System.out.println("[2] Agregar Dinero\n");
            System.out.println("[3] Retirar Dinero\n");
            System.out.println("[4] Ver Historial de Retiros de Dinero\n");
            System.out.println("[5] Volver al Menu de Gestion de Stock del Local\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();
            System.out.println("\n");
            switch (opcion) {
                case 1:
                    local.ObtenerLocalDelArchivo();
                    System.out.println(local.getRecaudacion());
                    local.AgregarLocalAlArchivo();
                    break;
                case 2:
                    local.ObtenerLocalDelArchivo();
                    System.out.println("Ingrese la cantidad que desea Agregar: ");
                    double agregar= scanner.nextDouble();
                    local.agregarRecaudacion(agregar);
                    local.AgregarLocalAlArchivo();
                    break;
                case 3:
                    local.ObtenerLocalDelArchivo();
                    System.out.println("Ingrese la cantidad que desea Retirar: ");
                    double retirar= scanner.nextDouble();
                    local.retirarDinero(retirar);
                    System.out.println("Retirado con Exito!");
                    local.AgregarLocalAlArchivo();
                    break;
                case 4:
                    local.ObtenerLocalDelArchivo();
                    System.out.println(local.imprimirRetiros());
                    local.AgregarLocalAlArchivo();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                    System.out.println("\n\n\n\n");
                    break;
            }
        }
    }
    public static void subMenuGestionEmpleados(Local local){
        boolean salir=false;

        while(!salir){
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n--- Gestion de Empleados: \n");
            System.out.println("[1] Ingresar un Nuevo Empleado\n");
            System.out.println("[2] Ver Empleados\n");
            System.out.println("[3] Editar Informacion de Empleados\n");
            System.out.println("[4] Dar de Baja un Empleado\n");
            System.out.println("[5] Dar de Alta un Empleado\n");
            System.out.println("[6] Exportar Empleados a Formato .JSON\n");
            System.out.println("[7] Volver al Menu de Gestion del Local\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();
            System.out.println("\n");
            switch (opcion){
                case 1:
                    cargarEmpleadosAlLocal(local);
                    break;
                case 2:
                    local.ObtenerEmpleadosDelArchivo();
                    System.out.println(local.imprimirEmpleados());
                    break;
                case 3:
                    local.ObtenerEmpleadosDelArchivo();
                    System.out.println(local.imprimirEmpleados());
                    System.out.println("Ingrese el ID del Empleado que desea editar: ");
                    int editar= validarEntero();
                    boolean salirAux = false;
                    while (!salirAux) {
                        System.out.println("\n---------------------------------------------------\n");
                        System.out.println("\n---- Editar Informacion de Empleado: \n");
                        System.out.println("[1] Editar el Nombre y Apellido\n");
                        System.out.println("[2] Editar el Horario\n");
                        System.out.println("[3] Modificar el Salario\n");
                        System.out.println("[4] Editar DNI\n");
                        System.out.println("[5] Volver al Menu de Gestion de Empleados\n");
                        System.out.print("Ingrese su opción: ");
                        int opcionAux = validarEntero();
                        System.out.println("\n");
                        switch (opcionAux) {
                            case 1:
                                System.out.println("Ingrese el nuevo nombre: ");
                                String nuevoNombre=validarString();
                                System.out.println("Ingrese el nuevo apellido: ");
                                String nuevoApellido=validarString();
                                local.editarNombreCompletoEmpleado(editar,nuevoNombre,nuevoApellido);
                                local.AgregarEmpleadosAlArchivo();
                                break;
                            case 2:
                                System.out.println("Ingrese el nuevo horario: ");
                                String nuevoHorario=validarHorario();
                                local.editarHorariosEmpleado(editar,nuevoHorario);
                                local.AgregarEmpleadosAlArchivo();
                                break;
                            case 3:
                                System.out.println("Ingrese el nuevo salario: ");
                                double nuevoSalario=validarDouble();
                                local.editarSalarioEmpleado(editar,nuevoSalario);
                                local.AgregarEmpleadosAlArchivo();
                                break;
                            case 4:
                                System.out.println("Ingrese el nuevo DNI: ");
                                String nuevoDNI = validarDNI();
                                local.editarDNIEmpleado(editar, nuevoDNI);
                                local.AgregarEmpleadosAlArchivo();
                                break;
                            case 5:
                                salirAux=true;
                                break;
                            default:
                                System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                                System.out.println("\n\n\n\n");
                                break;
                        }
                    }
                    break;
                case 4:
                        local.ObtenerEmpleadosDelArchivo();
                        System.out.println(local.imprimirEmpleados());
                        System.out.println("Ingrese el ID del Empleado que desea dar de Baja: ");
                        int darDeBaja = scanner.nextInt();
                        System.out.println(local.manejarDarDeBajaEmpleado(darDeBaja));
                        local.AgregarEmpleadosAlArchivo();
                    break;
                case 5:
                    local.ObtenerEmpleadosDelArchivo();
                    if (!local.hayEmpleadosDadosDeBaja()) {
                        System.out.println("No hay empleados dados de baja para dar de alta.");
                    } else {
                        System.out.println(local.imprimirEmpleadosDadosDeBaja());
                        System.out.println("Ingrese el ID del Empleado que desea dar de Alta: ");
                        int darDeAlta = scanner.nextInt();
                        System.out.println(local.manejarDarDeAltaEmpleado(darDeAlta));
                        local.AgregarEmpleadosAlArchivo();
                    }
                    break;
                case 6:
                    local.exportarEmpleados();
                    System.out.println("Empleados exportados exitosamente a JSON.");
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                    System.out.println("\n\n\n\n");
                    break;
            }
    }
    }
    public static void subMenuGestionClientesLocal (Local local){
        boolean salir = false;

        while (!salir) {
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n--- Gestion de Clientes del Local: \n");
            System.out.println("[1] Ver el Historial de Clientes del Local\n");
            System.out.println("[2] Exportar el Historial de Clientes del Local en Formarto .JSON\n");
            System.out.println("[3] Volver al Menu de Gestion del Local\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();
            System.out.println("\n");
            switch (opcion) {
                case 1:
                    local.limpiarClientes();
                    local.ObtenerClientesDelArchivo();
                    System.out.println(local.imprimirClientes());
                    break;
                case 2:
                    local.ObtenerClientesDelArchivo();
                    local.exportarClientes();
                    System.out.println("Clientes exportados correctamente.");
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                    System.out.println("\n\n\n\n");
                    break;
            }
        }
    }
    public static void subMenuGestionStockLocal (Local local){
        boolean salir = false;

        while (!salir) {
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n--- Gestion de Stock del Local: \n");
            System.out.println("[1] Ingresar Ropa al Stock\n");
            System.out.println("[2] Ver el Stock Disponible\n");
            System.out.println("[3] Editar Ropa\n");
            System.out.println("[4] Devolucion de Ropa\n");
            System.out.println("[5] Filtrar Stock de Ropa\n");
            System.out.println("[6] Dar de Baja Ropa del Stock\n");
            System.out.println("[7] Dar de Alta Ropa del Stock\n");
            System.out.println("[8] Exportar el Stock de Ropa en Formarto .JSON\n");
            System.out.println("[9] Volver al Menu de Gestion del Local\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();

            System.out.println("\n");
            switch (opcion) {
                case 1:
                    cargarRopaAlStock(local);
                    break;
                case 2:
                    local.ObtenerRopaDelArchivo();
                    System.out.println(local.mostrarStockRopa());
                    break;
                case 3:
                    local.ObtenerRopaDelArchivo();
                    System.out.println(local.mostrarStockRopa());
                    System.out.println("Seleccione el ID de la Prenda que desea editar:");
                    int idPrenda = scanner.nextInt();
                    scanner.nextLine();
                    Ropa prendaAEditar = local.buscarRopaPorId(idPrenda);
                    if (prendaAEditar != null) {
                        boolean editar = true;
                        while (editar) {
                            System.out.println("\n----Edicion de Ropa: \n");
                            System.out.println("[1] Precio\n");
                            System.out.println("[2] Stock\n");
                            System.out.println("[3] Finalizar edición\n");
                            int opcionEdicion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcionEdicion) {
                                case 1:
                                    System.out.println("Ingrese el nuevo precio:");
                                    double nuevoPrecio = validarDouble();
                                    local.cambiarPrecioRopa(idPrenda, nuevoPrecio);
                                    local.AgregarRopaAlArchivo();
                                    System.out.println("El precio de la prenda ha sido actualizado.");
                                    break;

                                case 2:
                                    System.out.println("Ingrese el nuevo stock:");
                                    int nuevoStock = scanner.nextInt();
                                    scanner.nextLine();
                                    local.cambiarStockRopa(idPrenda, nuevoStock);
                                    local.AgregarRopaAlArchivo();
                                    System.out.println("El stock de la prenda ha sido actualizado.");
                                    break;
                                case 3:
                                    editar = false;
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("No se encontró la prenda con el ID especificado.");
                    }
                    break;
                case 4:
                    devolverRopa(local);
                    break;
                case 5:
                    subMenuVerificacionStock(local);
                    break;
                case 6:
                    local.ObtenerRopaDelArchivo();
                    System.out.println(local.mostrarStockRopa());
                    System.out.println("Seleccione el ID de la Prenda que Desea dar de Baja:");
                    int darDeBaja = scanner.nextInt();
                    System.out.println(local.manejarDarDeBajaRopa(darDeBaja));
                    local.AgregarRopaAlArchivo();
                    break;
                case 7:
                    local.ObtenerRopaDelArchivo();
                    if (!local.hayRopaDadaDeBaja()) {
                        System.out.println("No hay ropa para dar de alta.");
                    } else {
                        System.out.println(local.mostrarStockRopaNoDisponible());
                        System.out.println("Seleccione el ID de la Prenda que Desea dar de Alta:");
                        int darDeAlta = scanner.nextInt();
                        System.out.println(local.manejarDarDeAltaRopa(darDeAlta));
                        local.AgregarRopaAlArchivo();
                    }
                    break;
                case 8:
                    local.ObtenerRopaDelArchivo();
                    local.exportarStockRopa();
                    System.out.println("El stock de ropa ha sido exportado en formato JSON a 'Stock_De_Ropa_Exportado.json'");
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                    System.out.println("\n\n\n\n");
                    break;
            }
        }
    }
    public static void subMenuVerificacionStock (Local local){
        boolean salir = false;

        while (!salir) {
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n--- Filtrar el Stock: \n");
            System.out.println("[1] Filtrar Ropa por Tipo\n");
            System.out.println("[2] Filtrar Ropa por Talle\n");
            System.out.println("[3] Filtrar Ropa por Color\n");
            System.out.println("[4] Volver al Menu de Gestion de Stock del Local\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();
            System.out.println("\n");
            switch (opcion) {
                case 1:
                    filtrarPorTipo(local);
                    break;
                case 2:
                    filtrarPorTalle(local);
                    break;
                case 3:
                    filtrarPorColor(local);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor,Ingrese de nuevo la opcion que desea\n");
                    System.out.println("\n\n\n\n");
                    break;
            }
            }
    }
    public static void menuRealizarCompra(Local local) {
        local.limpiarClientes();
        local.ObtenerClientesDelArchivo();
        System.out.println("Ingrese el DNI: ");
        String buscarDni = validarDNI();



        Cliente cliente = local.buscarClientePorDni(buscarDni);

        if (cliente == null) {
            cliente = agregarCliente(buscarDni);
        } else {
            System.out.println(cliente.mostrarHistorial());
        }

        HashMap<Ropa, Integer> prendasSeleccionadas = crearListaDeCompras(local);
        Compra compra = new Compra(prendasSeleccionadas);

        subMenuRealizarCompra(local, cliente, compra);
    }
    public static void subMenuRealizarCompra(Local local, Cliente cliente, Compra compra) {
        boolean salir = false;
        local.ObtenerLocalDelArchivo();
        while (!salir) {
            System.out.println("\n---------------------------------------------------\n");
            System.out.println("\n-- Gestion de la Compra: \n");
            System.out.println("[1] Ver la Lista de Compra\n");
            System.out.println("[2] Editar Lista de Compra\n");
            System.out.println("[3] Procesar la Compra\n");
            System.out.print("Ingrese su opción: ");

            int opcion = validarEntero();
            System.out.println("\n");
            switch (opcion) {
                case 1:
                    System.out.println(compra.imprimirItemsComprados());
                    break;
                case 2:
                    boolean editarLista = true;
                    while (editarLista) {
                        System.out.println("Seleccione una opción: \n");
                        System.out.println("[1] Agregar prenda\n");
                        System.out.println("[2] Eliminar prenda\n");
                        System.out.println("[3] Finalizar edición\n");
                        System.out.print("Ingrese su opción: ");
                        int opcionEdicion = validarEntero();
                        switch (opcionEdicion) {
                            case 1:
                                System.out.println(local.mostrarStockRopa());
                                System.out.print("Seleccione el ID de la prenda que desea agregar: ");
                                int idPrendaAgregar = validarEntero();
                                Ropa prendaAAgregar = local.buscarRopaPorId(idPrendaAgregar);
                                if (prendaAAgregar != null) {
                                    compra.agregarItems(prendaAAgregar);
                                    System.out.println("Prenda agregada a la lista de compra.");
                                    prendaAAgregar.bajarUnStock();
                                } else {
                                    System.out.println("No se encontró la prenda con el ID especificado.");
                                }
                                break;
                            case 2:
                                System.out.println("Lista de compra actual:");
                                System.out.println(compra.imprimirItemsComprados());
                                System.out.print("Ingrese el ID de la prenda que desea eliminar: ");
                                int idPrendaEliminar = scanner.nextInt();
                                Ropa prendaAEliminar = null;
                                for (Map.Entry<Ropa, Integer> entry : compra.getItemsComprados().entrySet()) {
                                    if (entry.getKey().getId() == idPrendaEliminar) {
                                        prendaAEliminar = entry.getKey();
                                        break;
                                    }
                                }
                                if (prendaAEliminar != null) {
                                    compra.eliminarItem(prendaAEliminar);
                                    System.out.println("Prenda eliminada de la lista de compra.");
                                    prendaAEliminar.subirUnStock();
                                } else {
                                    System.out.println("No se encontró la prenda en la lista de compra.");
                                }
                                break;
                            case 3:
                                editarLista = false;
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    }
                    break;
                case 3:
                    if (compra.getItemsComprados().isEmpty()) {
                        System.out.println("La lista de compra está vacía. No se puede procesar la compra.");
                    } else {
                        salir = true;
                        cliente.setCompra(compra);
                        double totalCompra = compra.calcularTotal();
                        local.agregarRecaudacion(totalCompra);
                        local.AgregarLocalAlArchivo();
                        cliente.agregarAlHistorialDeCompras(compra);
                        local.agregarCliente(cliente);
                        local.AgregarClientesAlArchivo();
                        compra.crearPDF(local, cliente);
                    }
                    break;
                default:
                    System.out.println("\nOpción no válida. Por favor, ingrese de nuevo la opción que desea\n");
                    System.out.println("\n\n\n\n");
                    break;
            }
        }
    }
    public static Ropa agregarRopa(){
        System.out.println("Dime el stock: ");
        int stock = validarEntero();
        System.out.println("Dime el tipo de prenda: ");
        String prenda = validarString();
        System.out.println("Dime el talle (XS, S, M, L, XL, XXL): ");
        String talleAux = scanner.nextLine().toUpperCase();
        Talle talle = Talle.valueOf(talleAux);
        System.out.println("Dime el precio: ");
        double precio = validarDouble();
        System.out.println("Dime el color de la prenda: ");
        String color = validarString();
        Ropa ropaAux = new Ropa(stock, prenda, talle, precio, color);
        return ropaAux;
    }
    public static void cargarRopaAlStock(Local localAux){
        int op = 0;
        localAux.ObtenerRopaDelArchivo();
        while(op==0){
            localAux.agregarRopaAlStock(agregarRopa());
            System.out.println("Desea agregar otra prenda?\n(Escribi 0 si queres)");
            op=scanner.nextInt();
            scanner.nextLine();
        }
        localAux.AgregarRopaAlArchivo();
    }
    public static Empleado crearEmpleado(){

        System.out.println("Dime el apellido");
        String apellido = validarString();

        System.out.println("Dime el nombre");
        String nombre = validarString();

        System.out.println("Dime el dni");
        String dni = validarDNI();

        System.out.println("Dime el horario: ");
        String horario = validarHorario();

        System.out.println("Dime el salario: ");
        double salario = validarDouble();

        Empleado emp = new Empleado(nombre, apellido, dni, salario, horario);

        return emp;
    }
    public static void cargarEmpleadosAlLocal (Local localAux){
        int op = 0;
        localAux.ObtenerEmpleadosDelArchivo();
        System.out.println("------Inicio Registro Empleado------\n");
        while(op==0){
            Empleado emp=crearEmpleado();
            localAux.agregarEmpleado(emp);
            localAux.AgregarEmpleadosAlArchivo();
            System.out.println("------Empleado Registrado Exitosamente!------\n");
            System.out.println("Desea agregar otro Empleado?\n(Escribi 0 si queres)");
            op=scanner.nextInt();
            scanner.nextLine();
        }
        localAux.AgregarEmpleadosAlArchivo();
    }
    public static Cliente agregarCliente(String dni){
        System.out.println("------Inicio Registro Cliente------\n");

        System.out.println("Dime el apellido");
        String apellido = validarString();

        System.out.println("Dime el nombre");
        String nombre = validarString();

        Cliente cliente = new Cliente (nombre, apellido, dni);
        System.out.println("------Cliente Registrado Exitosamente!------\n");
        return cliente;
    }
    public static HashMap<Ropa, Integer> crearListaDeCompras(Local local) {
        HashMap<Ropa, Integer> prendasSeleccionadas = new HashMap<>();

        int op = 0;
        System.out.println("------Inicio Lista de Compras------\n");

        while (op == 0) {
            local.ObtenerRopaDelArchivo();

            System.out.println(local.mostrarStockRopa());
            System.out.println("Seleccione el ID de la Prenda que desea:");

            int index = scanner.nextInt();
            scanner.nextLine();

            Ropa seleccionada = local.buscarRopaPorId(index);

            if (seleccionada != null && seleccionada.isDisponibilidad()) {
                if (seleccionada.getStock() > 0) {
                    if (prendasSeleccionadas.containsKey(seleccionada)) {
                        int cantidad = prendasSeleccionadas.get(seleccionada);
                        prendasSeleccionadas.put(seleccionada, cantidad + 1);
                    } else {
                        prendasSeleccionadas.put(seleccionada, 1);
                    }

                    seleccionada.bajarUnStock();
                    local.AgregarRopaAlArchivo();
                } else {
                    System.out.println("No hay stock disponible para la prenda seleccionada.");
                }
            } else {
                System.out.println("La prenda seleccionada no está disponible.");
            }

            System.out.print("¿Desea seleccionar otra prenda? Pulse 0 si así lo desea : ");
            op = scanner.nextInt();
        }

        return prendasSeleccionadas;
    }
    public static void filtrarPorTipo(Local local){
        System.out.println("Ingrese el Tipo de Prenda que desea Filtrar: ");
        String tipo=validarString();

        local.ObtenerRopaDelArchivo();
        System.out.println(local.filtrarRopaPorTipo(tipo));
    }
    public static void filtrarPorTalle(Local local){
        System.out.println("Ingrese el Talle de Prenda que desea Filtrar: ");
        String talle = scanner.nextLine().toUpperCase();
        Talle tipo = Talle.valueOf(talle);

        local.ObtenerRopaDelArchivo();
        System.out.println(local.filtrarRopaPorTalle(tipo));
    }
    public static void filtrarPorColor(Local local){
        System.out.println("Ingrese el Color de las Prendas que desea Filtrar: ");
        String color=validarString();

        local.ObtenerRopaDelArchivo();
        System.out.println(local.filtrarRopaPorColor(color));
    }
    public static void devolverRopa(Local local) {
        System.out.println("\n--- Devolución de Ropa ---\n");
        System.out.print("Ingrese el DNI del cliente que realizó la compra: ");
        String dniCliente = validarDNI();
        local.ObtenerClientesDelArchivo();
        Cliente cliente = local.buscarClientePorDni(dniCliente);

        if (cliente != null) {
            System.out.println(local.mostrarStockRopa());
            System.out.print("Ingrese el ID de la prenda que desea devolver: ");
            int idPrendaDevuelta = scanner.nextInt();
            scanner.nextLine();
            Ropa prendaDevuelta = local.buscarRopaPorId(idPrendaDevuelta);
            if (prendaDevuelta != null) {
                if (prendaDevuelta.isDisponibilidad()) {
                    local.devolverRopaAlStock(idPrendaDevuelta);
                    local.AgregarRopaAlArchivo();
                    System.out.println("\nLa prenda volvió al Stock!");
                } else {
                    System.out.println("La prenda seleccionada no está disponible para devolución.");
                }
            } else {
                System.out.println("No se encontró la prenda con el ID especificado.");
            }
        } else {
            System.out.println("El DNI ingresado no corresponde a ningún cliente. No se puede realizar la devolución.");
        }
}
    private static String validarString() {
        String input = scanner.nextLine().trim();
        while (input.isEmpty() || contieneNumeros(input)) {
            System.out.println("Por favor, ingrese un string válido que no contenga números:");
            input = scanner.nextLine().trim();
        }
        return input;
    }
    private static boolean contieneNumeros(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    private static double validarDouble() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (esDouble(input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Debe ingresar un número double válido. Por favor, inténtelo nuevamente:");
            }
        }
    }
    private static boolean esDouble(String input) {
        return input.matches("-?\\d*\\.?\\d+");
    }
    private static String validarDNI() {
        String input = scanner.nextLine().trim();
        while (!input.matches("\\d{8}|\\d{8}-[A-Za-z]")) {
            System.out.println("Por favor, ingrese un DNI válido:");
            input = scanner.nextLine().trim();
        }
        return input;
    }
    private static String validarHorario() {
        String input = scanner.nextLine().trim();
        String regex = "\\d{2}:\\d{2}-\\d{2}:\\d{2}";

        while (!input.matches(regex)) {
            System.out.println("Formato de horario no válido. Por favor, ingrese un horario válido (por ejemplo, 09:00-10:00):");
            input = scanner.nextLine().trim();
        }

        return input;
    }
    private static int validarEntero() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (esEntero(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("No ha ingresado un número entero válido. Por favor, intente nuevamente:");
            }
        }
    }
    private static boolean esEntero(String input) {
        return Pattern.matches("-?\\d+", input);
    }
    private static String validarTelefono() {
        String telefono = scanner.nextLine().trim();
        while (!telefono.matches("\\d{10}")) {
            System.out.println("Por favor, ingrese un número de teléfono válido (10 dígitos):");
            telefono = scanner.nextLine().trim();
        }
        return telefono;
    }
}





