package cuvet.model;

public class Permiso {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String modulo;

    public Permiso() {}

    public Permiso(String codigo, String nombre, String modulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.modulo = modulo;
    }

    public static Permiso verFacturas() { return new Permiso("VER_FACTURAS", "Ver facturas", "FACTURACION"); }
    public static Permiso crearFacturas() { return new Permiso("CREAR_FACTURAS", "Crear facturas", "FACTURACION"); }
    public static Permiso verReportes() { return new Permiso("VER_REPORTES", "Ver reportes", "REPORTES"); }
    public static Permiso gestionarClientes() { return new Permiso("GESTIONAR_CLIENTES", "Gestionar clientes", "CLIENTES"); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public String getModulo() { return modulo; }
    public void setModulo(String modulo) { this.modulo = modulo; }
}