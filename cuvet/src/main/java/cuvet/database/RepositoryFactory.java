package cuvet.database;

/**
 * Fábrica centralizada de repositorios del sistema CUVET.
 * @author Cartagena Saco, Jose Alejandro (2310405)
 */

public final class RepositoryFactory {
    private static volatile RepositoryFactory instancia;
    private RepositoryFactory() {}
    public static RepositoryFactory getInstancia() {
        if (instancia == null) {
            synchronized (RepositoryFactory.class) {
                if (instancia == null) {
                    instancia = new RepositoryFactory();
                }
            }
        }
        return instancia;
    }
    public ClienteRepository clienteRepository() { return new ClienteRepository(); }
    public MascotaRepository mascotaRepository() { return new MascotaRepository(); }
    public ServicioRepository servicioRepository() { return new ServicioRepository(); }
    public AtencionRepository atencionRepository() { return new AtencionRepository(); }
    public AtencionServicioRepository atencionServicioRepository() { return new AtencionServicioRepository(); }
    public FacturaRepository facturaRepository() { return new FacturaRepository(); }
    public ItemFacturaRepository itemFacturaRepository() { return new ItemFacturaRepository(); }
    public UsuarioRepository usuarioRepository() { return new UsuarioRepository(); }
    public MedicamentoRepository medicamentoRepository() { return new MedicamentoRepository(); }
    public VacunaRepository vacunaRepository() { return new VacunaRepository(); }
    public CitaRepository citaRepository() { return new CitaRepository(); }
    public LogAuditoriaRepository logAuditoriaRepository() { return new LogAuditoriaRepository(); }
    public HistorialClinicoRepository historialClinicoRepository() { return new HistorialClinicoRepository(); }
    public ReporteRepository reporteRepository() { return new ReporteRepository(); }
    public DashboardRepository dashboardRepository() { return new DashboardRepository(); }
    public EstadisticasRepository estadisticasRepository() { return new EstadisticasRepository(); }
}