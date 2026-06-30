package cuvet.model;

/**
 * Examen de laboratorio solicitado para una atención.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class ExamenLaboratorio {
    private int id;
    private int idAtencion;
    private TipoExamen tipoExamen;
    private EstadoExamen estado;

    public enum TipoExamen {
        HEMOGRAMA, ECOGRAFIA, RADIOGRAFIA, UROANALISIS, BIOQUIMICA
    }

    public enum EstadoExamen {
        PENDIENTE, COMPLETADO
    }

    public ExamenLaboratorio() { this.estado = EstadoExamen.PENDIENTE; }

    public ExamenLaboratorio(int idAtencion, TipoExamen tipoExamen) {
        this();
        this.idAtencion = idAtencion;
        this.tipoExamen = tipoExamen;
    }

    public void completar() { this.estado = EstadoExamen.COMPLETADO; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public TipoExamen getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(TipoExamen tipoExamen) { this.tipoExamen = tipoExamen; }
    public EstadoExamen getEstado() { return estado; }
}