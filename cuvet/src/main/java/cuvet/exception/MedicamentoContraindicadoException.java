package cuvet.exception;
/** Lanzada al recetar un medicamento al que la mascota es alérgica. @author Becerra Huillcas, Gianella Emely (2411438) */
public class MedicamentoContraindicadoException extends RuntimeException {
    public MedicamentoContraindicadoException(String message) { super(message); }
    public MedicamentoContraindicadoException(String message, Throwable cause) { super(message, cause); }
}