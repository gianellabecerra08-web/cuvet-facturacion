package cuvet.model;

public class Raza {
    private int id;
    private String nombre;
    private int idEspecie;
    private String caracteristicas;
    private double pesoPromedioKg;

    public Raza() {}

    public Raza(String nombre, int idEspecie) {
        this.nombre = nombre;
        this.idEspecie = idEspecie;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getIdEspecie() { return idEspecie; }
    public void setIdEspecie(int id) { this.idEspecie = id; }
    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String c) { this.caracteristicas = c; }
    public double getPesoPromedioKg() { return pesoPromedioKg; }
    public void setPesoPromedioKg(double p) { this.pesoPromedioKg = p; }
}