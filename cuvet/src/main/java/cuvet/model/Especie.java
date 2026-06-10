package cuvet.model;

import java.util.ArrayList;
import java.util.List;

public class Especie {
    private int id;
    private String nombre;
    private String descripcion;
    private List<Raza> razas;

    public Especie() { this.razas = new ArrayList<>(); }

    public Especie(String nombre) {
        this();
        this.nombre = nombre;
    }

    public void agregarRaza(Raza raza) { if (raza != null) razas.add(raza); }

    public static Especie perro() { return new Especie("Perro"); }
    public static Especie gato() { return new Especie("Gato"); }
    public static Especie conejo() { return new Especie("Conejo"); }
    public static Especie ave() { return new Especie("Ave"); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public List<Raza> getRazas() { return razas; }
}
