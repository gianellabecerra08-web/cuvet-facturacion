package cuvet.model;

public class Direccion {
    private int id;
    private String calle;
    private String numero;
    private String distrito;
    private String provincia;
    private String departamento;
    private String referencia;

    public Direccion() {}

    public Direccion(String calle, String numero, String distrito) {
        this.calle = calle;
        this.numero = numero;
        this.distrito = distrito;
    }

    public String getDireccionCompleta() {
        return calle + " " + numero + ", " + distrito + ", " + provincia + ", " + departamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String d) { this.departamento = d; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
}