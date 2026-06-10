package cuvet.model;

import java.time.LocalDateTime;

public class Comprobante implements Exportable {
    private int id;
    private int idFactura;
    private String tipoComprobante;
    private String serie;
    private int numero;
    private LocalDateTime fechaEmision;
    private String rutaArchivoPDF;
    private boolean enviado;

    public Comprobante() {
        this.enviado = false;
        this.fechaEmision = LocalDateTime.now();
    }

    public Comprobante(int idFactura, String tipoComprobante, String serie, int numero) {
        this();
        this.idFactura = idFactura;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numero = numero;
    }

    public String getCodigoCompleto() {
        return tipoComprobante + "-" + serie + "-" + String.format("%08d", numero);
    }

    @Override public byte[] exportarPDF() { return new byte[0]; }
    @Override public String exportarCSV() { return getCodigoCompleto() + "," + fechaEmision; }
    @Override public String getNombreArchivo() { return "Comprobante_" + getCodigoCompleto() + ".pdf"; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int id) { this.idFactura = id; }
    public String getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(String t) { this.tipoComprobante = t; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime f) { this.fechaEmision = f; }
    public String getRutaArchivoPDF() { return rutaArchivoPDF; }
    public void setRutaArchivoPDF(String r) { this.rutaArchivoPDF = r; }
    public boolean isEnviado() { return enviado; }
    public void setEnviado(boolean enviado) { this.enviado = enviado; }
}