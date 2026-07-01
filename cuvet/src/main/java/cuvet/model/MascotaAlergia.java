package cuvet.model;

/**
 * Asociación entre una mascota y una alergia registrada, con su severidad.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class MascotaAlergia {
    private int id;
    private int idMascota;
    private int idAlergia;
    private String severidad; // "LEVE", "MODERADA", "GRAVE"

    public MascotaAlergia() {}

    public MascotaAlergia(int idMascota, int idAlergia, String severidad) {
        this.idMascota = idMascota;
        this.idAlergia = idAlergia;
        this.severidad = severidad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public int getIdAlergia() { return idAlergia; }
    public void setIdAlergia(int idAlergia) { this.idAlergia = idAlergia; }
    public String getSeveridad() { return severidad; }
    public void setSeveridad(String severidad) { this.severidad = severidad; }
}