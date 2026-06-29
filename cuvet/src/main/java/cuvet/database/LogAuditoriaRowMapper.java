package cuvet.database;

import cuvet.model.LogAuditoria;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapeador de filas SQL a entidad LogAuditoria.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public class LogAuditoriaRowMapper implements RowMapper<LogAuditoria> {
    @Override
    public LogAuditoria mapRow(ResultSet rs) throws SQLException {
        LogAuditoria log = new LogAuditoria(
                rs.getInt("id_usuario"),
                rs.getString("accion"),
                rs.getString("tabla"),
                rs.getInt("id_registro"),
                rs.getString("detalle")
        );
        log.setId(rs.getInt("id"));
        return log;
    }
}
