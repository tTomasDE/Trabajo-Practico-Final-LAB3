package Modelo.Interfaces;

import Modelo.Finanzas.Compra;
import Modelo.Humanos.Cliente;
import Modelo.Mercaderia.Ropa;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public interface Exportable {
    public void exportarStockRopa();
    public void exportarClientes();
}
