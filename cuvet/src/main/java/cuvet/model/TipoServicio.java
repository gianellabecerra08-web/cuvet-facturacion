package cuvet.model;

public class TipoServicio {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private boolean activo;

    public TipoServicio() { this.activo = true; }

    public TipoServicio(String codigo, String nombre) {
        this();
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static TipoServicio consulta() { return new TipoServicio("CONSULTA", "Consulta médica"); }
    public static TipoServicio cirugia() { return new TipoServicio("CIRUGIA", "Cirugía"); }
    public static TipoServicio vacuna() { return new TipoServicio("VACUNA", "Vacunación"); }
    public static TipoServicio medicamento() { return new TipoServicio("MEDICAMENTO", "Medicamento"); }
    public static TipoServicio hospitalizacion() { return new TipoServicio("HOSPITALIZACION", "Hospitalización"); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}