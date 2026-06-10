package cuvet.model;

public interface Exportable {
    byte[] exportarPDF();
    String exportarCSV();
    String getNombreArchivo();
}