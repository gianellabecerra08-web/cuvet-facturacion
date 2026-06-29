package cuvet.database;

/**
 * Enumerado de tablas del esquema CUVET.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public enum Tabla {
    USUARIOS("usuarios"),
    CLIENTES("clientes"),
    MASCOTAS("mascotas"),
    SERVICIOS("servicios"),
    ATENCIONES("atenciones"),
    ATENCION_SERVICIOS("atencion_servicios"),
    FACTURAS("facturas"),
    FACTURA_ITEMS("factura_items"),
    MEDICAMENTOS("medicamentos"),
    VACUNAS("vacunas"),
    CITAS("citas"),
    LOG_AUDITORIA("log_auditoria");
    private final String nombre;
    Tabla(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
}
