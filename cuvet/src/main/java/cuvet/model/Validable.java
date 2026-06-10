package cuvet.model;

import java.util.List;

public interface Validable {
    boolean esValido();
    List<String> obtenerErrores();
    void validar() throws Exception;
}