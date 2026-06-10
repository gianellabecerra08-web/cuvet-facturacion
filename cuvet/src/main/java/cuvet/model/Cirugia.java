package cuvet.model;

public class Cirugia extends Servicio {
    private String tipoCirugia;
    private int duracionEstimadaMinutos;
    private String anestesia;
    private boolean requiereHospitalizacion;
    private String cuidadosPostoperatorios;

    public Cirugia() { super(); }

    public Cirugia(String descripcion, double precioBase, String tipoCirugia, int duracionMinutos) {
        super(descripcion, TipoServicio.cirugia(), precioBase);
        this.tipoCirugia = tipoCirugia;
        this.duracionEstimadaMinutos = duracionMinutos;
    }

    public String getTipoCirugia() { return tipoCirugia; }
    public void setTipoCirugia(String t) { this.tipoCirugia = t; }
    public int getDuracionEstimadaMinutos() { return duracionEstimadaMinutos; }
    public void setDuracionEstimadaMinutos(int d) { this.duracionEstimadaMinutos = d; }
    public String getAnestesia() { return anestesia; }
    public void setAnestesia(String a) { this.anestesia = a; }
    public boolean isRequiereHospitalizacion() { return requiereHospitalizacion; }
    public void setRequiereHospitalizacion(boolean r) { this.requiereHospitalizacion = r; }
    public String getCuidadosPostoperatorios() { return cuidadosPostoperatorios; }
    public void setCuidadosPostoperatorios(String c) { this.cuidadosPostoperatorios = c; }
}