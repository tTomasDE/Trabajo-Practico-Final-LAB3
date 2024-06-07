package Modelo.Humanos;

import java.io.Serializable;
import java.util.Objects;

public class Empleado extends Persona {
    private static final long serialVersionUID = 5523004930622674062L;
    private double salario;
    private boolean disponible;
    private String horarios;

    public Empleado (){
        super();
        this.salario = 0;
        this.disponible=false;
        this.horarios ="";
    }

    public Empleado(String nombre, String apellido, String dni, double salario, String horarios) {
        super(nombre, apellido, dni);
        this.salario = salario;
        this.disponible=true;
        this.horarios = horarios;
    }

    public double getSalario() {
        return salario;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return super.toString()+" | Salario = " + salario + " | Disponible? = " + disponible + " | Horarios ='" + horarios + " | \n";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Double.compare(salario, empleado.salario) == 0 && disponible == empleado.disponible && Objects.equals(horarios, empleado.horarios);
    }

}
