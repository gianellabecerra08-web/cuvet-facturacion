package cuvet.model;

import java.util.ArrayList;
import java.util.List;

public class Rol {
    private int id;
    private String nombre;
    private String descripcion;
    private List<Permiso> permisos;

    public Rol() { this.permisos = new ArrayList<>(); }

    public Rol(String nombre, String descripcion) {
        this();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void agregarPermiso(Permiso permiso) {
        if (permiso != null && !permisos.contains(permiso)) permisos.add(permiso);
    }

    public void removerPermiso(Permiso permiso) { permisos.remove(permiso); }

    public boolean tienePermiso(String codigo) {
        return permisos.stream().anyMatch(p -> p.getCodigo().equals(codigo));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public List<Permiso> getPermisos() { return permisos; }
}