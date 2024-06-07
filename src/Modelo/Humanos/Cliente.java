package Modelo.Humanos;

import Modelo.Envolventes.GestorLista;
import Modelo.Finanzas.Compra;

import java.util.ArrayList;

public class Cliente extends Persona {

    private GestorLista<Compra> historialCompras;
    private Compra compra;

    public Cliente (){
        super();
        this.historialCompras= new GestorLista<>();
        this.compra=new Compra();
    }

    public Cliente(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
        this.historialCompras = new GestorLista<>();
    }

    public GestorLista<Compra> getHistorialCompras() {
        return historialCompras;
    }
    public void setCompra(Compra e){
        this.compra=e;
    }
    public void agregarAlHistorialDeCompras(Compra e){
        this.historialCompras.agregar(e);
    }
    public String mostrarHistorial (){
        String info="\nHistorial de Compras: \n\n";
        for(int i = 0; i < historialCompras.tamaño(); i++) {
            Compra com = historialCompras.obtener(i);
            info+="Fecha de Compra: "+com.getFechaDeCompra()+"\n"+com.imprimirItemsComprados()+"\n";
        }
        return info;
    }
    @Override
    public String toString() {
        return super.toString()+"\n\n"+mostrarHistorial()+"\n";

    }



}
