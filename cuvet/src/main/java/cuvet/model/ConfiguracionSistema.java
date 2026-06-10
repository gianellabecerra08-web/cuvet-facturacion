package cuvet.model;

public class ConfiguracionSistema {
    private int id;
    private String clave;
    private String valor;
    private String descripcion;
    private String tipo;

    public ConfiguracionSistema() {}

    public ConfiguracionSistema(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public static ConfiguracionSistema igv() { return new ConfiguracionSistema("IGV", "0.18"); }
    public static ConfiguracionSistema nombreEmpresa() { return new ConfiguracionSistema("NOMBRE_EMPRESA", "Clínica Veterinaria CUVET"); }
    public static ConfiguracionSistema serieBoleta() { return new ConfiguracionSistema("SERIE_BOLETA", "B001"); }
    public static ConfiguracionSistema serieFactura() { return new ConfiguracionSistema("SERIE_FACTURA", "F001"); }

    public double getValorNumerico() {
        try { return Double.parseDouble(valor); }
        catch (NumberFormatException e) { return 0.0; }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}