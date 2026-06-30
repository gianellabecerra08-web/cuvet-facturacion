package cuvet.model;

/**
 * Espacio físico de internamiento disponible en la clínica.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public class Canil {
    private int id;
    private int numero;
    private String tamano; // "PEQUENO", "MEDIANO", "GRANDE"
    private boolean disponible;

    public Canil() { this.disponible = true; }

    public Canil(int numero, String tamano) {
        this.numero = numero;
        this.tamano = tamano;
        this.disponible = true;
    }

    public void ocupar() { this.disponible = false; }
    public void liberar() { this.disponible = true; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }
    public boolean isDisponible() { return disponible; }

    @Override
    public String toString() { return "Canil #" + numero + " (" + tamano + ")"; }
}