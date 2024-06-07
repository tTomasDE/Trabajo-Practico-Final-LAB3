package Modelo.Envolventes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestorLista <T> implements Serializable {
    private List<T> lista;

    public GestorLista() {
        this.lista = new ArrayList<>();
    }

    public List<T> getLista() {
        return lista;
    }

    public void agregar(T elemento) {
        lista.add(elemento);
    }

    public void eliminar(T elemento) {
        lista.remove(elemento);
    }

    public T obtener(int indice) {
        return lista.get(indice);
    }

    public int tamaño() {
        return lista.size();
    }

    public boolean contiene(T elemento) {
        return lista.contains(elemento);
    }

    public void limpiar() {
        lista.clear();
    }


}
