package cuvet.model;

/**
 * Roles de usuario en el sistema CUVET.
 * @author Becerra Huillcas, Gianella Emely (2411438)
 */
public enum Rol {
    RECEPCIONISTA("Recepcionista"),
    VETERINARIO("Veterinario"),
    ADMINISTRADOR("Administrador");

    private final String nombre;

    Rol(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }

    @Override
    public String toString() { return nombre; }
}
