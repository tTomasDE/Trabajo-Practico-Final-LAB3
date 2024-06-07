package Modelo.Finanzas;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Caja implements Serializable {

    private double recaudacion;
    private HashMap<String, Double> retirosPorFecha;

    public Caja() {
        this.recaudacion=0;
        this.retirosPorFecha= new HashMap<>();
    }

    public double getRecaudacion() {
        return recaudacion;
    }

    private String calcularFecha(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fechaActual.format(formatter);
        return fechaFormateada;
    }
    public void retirarDinero(double aRetirar) {
        if (aRetirar <= recaudacion) {
            recaudacion -= aRetirar;
            String fechaActual = calcularFecha();
            retirosPorFecha.put(fechaActual, aRetirar);
        }
    }
    public String obtenerRetirosPorFecha() {
        String retiros = "Retiros por Fecha:\n";
        for (Map.Entry<String, Double> entry : retirosPorFecha.entrySet()) {
            String fecha = entry.getKey();
            double cantidad = entry.getValue();
            retiros += "Fecha: " + fecha + ", Cantidad Retirada: " + cantidad + "\n";
        }
        return retiros;
    }
    public void agregarRecaudacion(double aAgregar){
        recaudacion+=aAgregar;
    }

}
