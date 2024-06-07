package Modelo.Mercaderia;

import Modelo.Excepciones.eSinStock;
import Modelo.Excepciones.eSinTalle;

import java.io.Serializable;
import java.util.Objects;

public class Ropa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int stock;
    private String tipo;
    private Talle talle;
    private double precio;
    private String colorRopa;
    private boolean disponibilidad;

    public Ropa() {
        this.stock = 0;
        this.tipo = "";
        this.talle = null;
        this.precio = 0;
        this.colorRopa = "";
        this.disponibilidad=false;
    }

    public Ropa(int stock, String tipo, Talle talle, double precio, String colorRopa) {
        this.stock = stock;
        this.tipo = tipo;
        this.talle = talle;
        this.precio = precio;
        this.colorRopa = colorRopa;
        this.disponibilidad=true;
    }

//////////////////////////////////////GETTER Y SETTERS///////////////////////////////////////

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getTipo() {
        return tipo;
    }
    public Talle getTalle() {
        return talle;
    }
    public double getPrecio() {
        return precio;
    }
    public String getColorRopa() {
        return colorRopa;
    }
    public boolean isDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////METODOS//////////////////////////////////////////////

    @Override
    public String toString() {
        return "| ID: "+getId() + "| Stock : " + stock + " |  Tipo: " + tipo +   " | Talle: " + talle  + " | Precio: " + precio + " | Color de Ropa: " + colorRopa + " |\n" ;
    }
    public String toStringParaListaDeCompra() {
        return "-ID: "+getId() + " -- Tipo: " + tipo +   " -- Talle: " + talle  + " -- Precio: " + precio + " -- Color de Ropa: " + colorRopa + " " ;
    }
    public void bajarUnStock(){
        stock--;
    }
    public void subirUnStock(){
        stock++;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ropa ropa = (Ropa) obj;
        return id == ropa.id; // Comparar usando el campo ID
    }
    public void validarStock(int stock) throws eSinStock {
        if (stock<=0) {
            throw new eSinStock("No hay mas stock");
        }
    }
    public void validarTalle(Talle talle) throws eSinTalle {
        if (this.talle!=talle) {
            throw new eSinTalle("Talle incorrecto");
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(id); // Usar solo el campo ID para el hashCode
    }
}
