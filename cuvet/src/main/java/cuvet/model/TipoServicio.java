package cuvet.model;

/**
 * Enumerado de tipos de servicio ofrecidos en CUVET.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public enum TipoServicio {
    CONSULTA("Consulta Médica", 50.00),
    CIRUGIA("Cirugía", 200.00),
    VACUNA("Vacunación", 30.00),
    MEDICAMENTO("Medicamento", 0.00),
    HOSPITALIZACION("Hospitalización", 80.00),
    LABORATORIO("Laboratorio/Análisis", 60.00);

    private final String descripcion;
    private final double precioBase;

    TipoServicio(String descripcion, double precioBase) {
        this.descripcion = descripcion;
        this.precioBase = precioBase;
    }

    public String getDescripcion() { return descripcion; }
    public double getPrecioBase() { return precioBase; }

    @Override
    public String toString() { return descripcion; }
}
