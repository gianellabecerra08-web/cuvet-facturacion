package cuvet.model;

/**
 * Catálogo de sustancias o principios activos que pueden causar alergia.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Alergia {
    private int id;
    private String descripcion;
    private String tipoSustancia; // "MEDICAMENTO", "ALIMENTO", "AMBIENTAL"

    public Alergia() {}

    public Alergia(String descripcion, String tipoSustancia) {
        this.descripcion = descripcion;
        this.tipoSustancia = tipoSustancia;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getTipoSustancia() { return tipoSustancia; }
    public void setTipoSustancia(String tipoSustancia) { this.tipoSustancia = tipoSustancia; }

    @Override
    public String toString() { return descripcion + " (" + tipoSustancia + ")"; }
}
