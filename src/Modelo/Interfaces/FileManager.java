package Modelo.Interfaces;

import Modelo.Humanos.Cliente;
import Modelo.Humanos.Empleado;
import Modelo.Local;
import Modelo.Mercaderia.Ropa;

import java.io.*;
import java.util.HashSet;

public interface FileManager {
    public void AgregarLocalAlArchivo();
    public Local ObtenerLocalDelArchivo();
    public void AgregarRopaAlArchivo ();
    public void ObtenerRopaDelArchivo ();
    public void AgregarEmpleadosAlArchivo ();
    public void ObtenerEmpleadosDelArchivo ();
    public void AgregarClientesAlArchivo ();
    public void ObtenerClientesDelArchivo();
}
