package cuvet.model;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getFechaCreacion();
    LocalDateTime getFechaModificacion();
    String getUsuarioCreacion();
    void setFechaCreacion(LocalDateTime fecha);
    void setFechaModificacion(LocalDateTime fecha);
    void setUsuarioCreacion(String usuario);
}