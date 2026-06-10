package cuvet.model;

public class Contacto {
    private int id;
    private String telefono;
    private String telefonoSecundario;
    private String email;
    private String redesSociales;

    public Contacto() {}

    public Contacto(String telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    public boolean tieneEmail() { return email != null && !email.isEmpty(); }
    public boolean tieneTelefonoSecundario() { return telefonoSecundario != null && !telefonoSecundario.isEmpty(); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String t) { this.telefono = t; }
    public String getTelefonoSecundario() { return telefonoSecundario; }
    public void setTelefonoSecundario(String t) { this.telefonoSecundario = t; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRedesSociales() { return redesSociales; }
    public void setRedesSociales(String r) { this.redesSociales = r; }
}