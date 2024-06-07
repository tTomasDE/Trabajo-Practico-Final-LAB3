package Modelo.Humanos;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    protected int id;
    private String nombre;
    private String apellido;
    private String dni;

    public Persona() {
        this.id=0;
        this.nombre="";
        this.apellido="";
        this.dni="";
    }

    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "ID = " + id + " | Nombre Completo: "+getApellido()+" "+getNombre()+" | DNI = " + dni;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
