package cuvet.model;

public class Consulta extends Servicio {
    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private boolean requiereSeguimiento;

    public Consulta() { super(); }

    public Consulta(String descripcion, double precioBase, String motivo) {
        super(descripcion, TipoServicio.consulta(), precioBase);
        this.motivoConsulta = motivo;
    }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String m) { this.motivoConsulta = m; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String d) { this.diagnostico = d; }
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String t) { this.tratamiento = t; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String o) { this.observaciones = o; }
    public boolean isRequiereSeguimiento() { return requiereSeguimiento; }
    public void setRequiereSeguimiento(boolean r) { this.requiereSeguimiento = r; }
}