package cuvet.model;

/**
 * Resultado individual obtenido de un examen de laboratorio.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class ResultadoAnalisis {
    private int id;
    private int idExamen;
    private String parametroAnalizado;
    private String valorObtenido;
    private String rangoReferencia;
    private String conclusiones;

    public ResultadoAnalisis() {}

    public ResultadoAnalisis(int idExamen, String parametroAnalizado, String valorObtenido, String rangoReferencia) {
        this.idExamen = idExamen;
        this.parametroAnalizado = parametroAnalizado;
        this.valorObtenido = valorObtenido;
        this.rangoReferencia = rangoReferencia;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdExamen() { return idExamen; }
    public void setIdExamen(int idExamen) { this.idExamen = idExamen; }
    public String getParametroAnalizado() { return parametroAnalizado; }
    public void setParametroAnalizado(String p) { this.parametroAnalizado = p; }
    public String getValorObtenido() { return valorObtenido; }
    public void setValorObtenido(String valorObtenido) { this.valorObtenido = valorObtenido; }
    public String getRangoReferencia() { return rangoReferencia; }
    public void setRangoReferencia(String r) { this.rangoReferencia = r; }
    public String getConclusiones() { return conclusiones; }
    public void setConclusiones(String conclusiones) { this.conclusiones = conclusiones; }
}