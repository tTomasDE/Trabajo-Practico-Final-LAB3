package Modelo.Envolventes;

import java.io.Serializable;
import java.util.HashSet;

public class GestorHashSet <T> implements Serializable {

    private HashSet<T> conjunto;

    public GestorHashSet() {
        conjunto = new HashSet<>();
    }

    public HashSet<T> getConjunto() {
        return conjunto;
    }

    public void agregar(T elemento) {
        conjunto.add(elemento);
    }

    public void eliminar(T elemento) {
        conjunto.remove(elemento);
    }

    public boolean contiene(T elemento) {
        return conjunto.contains(elemento);
    }

    public void limpiar() {
        conjunto.clear();
    }

    public int contarElementos() {
        return conjunto.size();
    }
}
