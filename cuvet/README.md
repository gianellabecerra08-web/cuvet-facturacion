# 🐾 CUVET — Sistema de Gestión y Facturación Integral

**Clínica Veterinaria CUVET**  
Curso: Programación Orientada a Objetos II  
Universidad Tecnológica del Perú — 2026-I  
Bloque: FC-VIRISI05A01 | Profesor: Roberto Josué Rodríguez Urquiaga

---

## 👥 Integrantes

| Integrante | Código | Paquete(s) |
|---|---|---|
| Lucero Silva, Freddy Estefano | 2312081 | `cuvet.controller` |
| Becerra Huillcas, Gianella Emely | 2411438 | `cuvet.model` + `cuvet.exception` |
| Cartagena Saco, Jose Alejandro | 2310405 | `cuvet.database` |
| Medina Peña, Danae Alexandra | 2211055 | `cuvet.view` + `cuvet.util` |

---

## 📋 Descripción

Sistema de escritorio desarrollado en **Java 17** con interfaz gráfica **Swing** y persistencia en **MySQL 8**, aplicando el patrón arquitectónico **MVC + Repository**. Resuelve la problemática de facturación desorganizada de la Clínica Veterinaria CUVET.

### Funcionalidades principales

- ✅ Registro de atenciones con validación de duplicados (RF03)
- ✅ Generación automática de facturas con cálculo de IGV 18 % (`BigDecimal`)
- ✅ Gestión de clientes y mascotas (CRUD completo)
- ✅ Historial clínico por mascota
- ✅ Reportes mensuales de ingresos por tipo de servicio
- ✅ Control de inventario de medicamentos con alerta de stock bajo
- ✅ Agenda de citas médicas con estados (PENDIENTE → CONFIRMADA → ATENDIDA)
- ✅ Autenticación por roles (RECEPCIONISTA, VETERINARIO, ADMINISTRADOR)
- ✅ Log de auditoría para todas las operaciones CRUD

---

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────┐
│         CAPA DE PRESENTACIÓN            │
│  cuvet.view  (10 clases Java Swing)     │
├─────────────────────────────────────────┤
│         CAPA DE CONTROLADOR             │
│  cuvet.controller  (9 controladores)    │
├─────────────────────────────────────────┤
│         CAPA DE DOMINIO (MODEL)         │
│  cuvet.model (15) + cuvet.exception (10)│
├─────────────────────────────────────────┤
│         CAPA DE PERSISTENCIA            │
│  cuvet.database  (IRepository + 11 impl)│
├─────────────────────────────────────────┤
│         INFRAESTRUCTURA                  │
│  MySQL 8 (cuvet_db) + cuvet.util (10)   │
└─────────────────────────────────────────┘
```

### Patrones implementados

| Patrón | Categoría | Clases |
|---|---|---|
| **Singleton** | Creacional | `DatabaseConnection`, `SessionManager`, `AuditoriaLogger` |
| **Repository** | Estructural | `IRepository<T,ID>` + 11 implementaciones |
| **MVC** | Arquitectural | `cuvet.view` / `controller` / `model` |
| **Template Method** | Comportamiento | `IRepository<T,ID>` |

### Programación funcional (Java 17)

- `Stream + map/reduce` para cálculo de subtotales en `Factura`
- `Optional<T>` en todos los repositorios con `.orElseThrow()`
- `Map.merge(tipo, monto, BigDecimal::add)` en `Reporte`
- Referencias a métodos (`BigDecimal::add`, `ItemFactura::getSubtotal`)

---

## 🛠️ Stack Tecnológico

| Componente | Tecnología | Versión |
|---|---|---|
| Lenguaje | Java | 17 LTS |
| Interfaz gráfica | Java Swing | JDK 17 |
| Base de datos | MySQL | 8.0 |
| Driver JDBC | MySQL Connector/J | 8.0.33 |
| Build | Maven | 3.9 |

---

## 🚀 Instalación y Ejecución

### Prerrequisitos

- Java JDK 17+
- MySQL Server 8.0+
- Maven 3.9+

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/[usuario]/cuvet.git
cd cuvet

# 2. Crear la base de datos
mysql -u root -p -e "CREATE DATABASE cuvet_db;"

# 3. Ejecutar el script SQL
mysql -u root -p cuvet_db < sql/cuvet_schema.sql

# 4. Configurar credenciales en:
#    src/main/java/cuvet/database/DatabaseConnection.java
#    (cambiar USER y PASS)

# 5. Compilar
mvn clean package

# 6. Ejecutar
java -jar target/cuvet-1.0.0.jar
```

### Credenciales por defecto

| Usuario | Contraseña | Rol |
|---|---|---|
| `admin` | `admin123` | Administrador |
| `recep1` | `admin123` | Recepcionista |
| `vet1` | `admin123` | Veterinario |

---

## 📁 Estructura del Proyecto

```
cuvet/
├── pom.xml
├── README.md
├── sql/
│   └── cuvet_schema.sql
└── src/main/java/cuvet/
    ├── Main.java
    ├── model/          ← Gianella Becerra (15 clases)
    │   ├── Atencion.java
    │   ├── Factura.java
    │   ├── Cliente.java
    │   ├── Mascota.java
    │   ├── Servicio.java
    │   ├── ItemFactura.java
    │   ├── Usuario.java
    │   ├── Medicamento.java
    │   ├── Vacuna.java
    │   ├── Cita.java
    │   ├── HistorialClinico.java
    │   ├── Reporte.java
    │   ├── LogAuditoria.java
    │   ├── TipoServicio.java (enum)
    │   └── Rol.java (enum)
    ├── exception/      ← Gianella Becerra (10 clases)
    │   ├── DuplicadoException.java
    │   ├── AuthException.java
    │   ├── ClienteNotFoundException.java
    │   ├── MascotaNotFoundException.java
    │   ├── ServicioNotFoundException.java
    │   ├── FacturaNotFoundException.java
    │   ├── DatabaseException.java
    │   ├── StockInsuficienteException.java
    │   ├── UsuarioInactivoException.java
    │   └── ReporteVacioException.java
    ├── database/       ← Jose Cartagena (12 clases)
    │   ├── IRepository.java (interfaz genérica)
    │   ├── DatabaseConnection.java (Singleton)
    │   ├── ClienteRepository.java
    │   ├── MascotaRepository.java
    │   ├── ServicioRepository.java
    │   ├── AtencionRepository.java
    │   ├── FacturaRepository.java
    │   ├── UsuarioRepository.java
    │   ├── MedicamentoRepository.java
    │   ├── VacunaRepository.java
    │   ├── CitaRepository.java
    │   └── LogAuditoriaRepository.java
    ├── controller/     ← Freddy Lucero (9 clases)
    │   ├── AuthController.java
    │   ├── AtencionController.java
    │   ├── FacturaController.java
    │   ├── ClienteController.java
    │   ├── ReporteController.java
    │   ├── ServicioController.java
    │   ├── MedicamentoController.java
    │   ├── CitaController.java
    │   └── VacunaController.java
    ├── view/           ← Danae Medina (10 clases)
    │   ├── LoginView.java
    │   ├── MainView.java
    │   ├── AtencionView.java
    │   ├── ClienteView.java
    │   ├── FacturaView.java
    │   ├── ReporteView.java
    │   ├── ServicioView.java
    │   ├── MedicamentoView.java
    │   ├── CitaView.java
    │   └── HistorialView.java
    └── util/           ← Danae Medina (10 clases)
        ├── SessionManager.java (Singleton)
        ├── AuditoriaLogger.java (Singleton)
        ├── PDFExporter.java
        ├── ReporteExporter.java
        ├── PasswordUtil.java
        ├── ValidadorDNI.java
        ├── ValidadorEmail.java
        ├── FormatUtil.java
        ├── NotificacionService.java
        └── Constantes.java
```

---

## 📊 Diagrama de Secuencia — CU01 Registrar Atención

```
Recepcionista → AtencionView → AtencionController → AtencionRepository
     |               |                |                      |
     | selecciona    |                |                      |
     | cliente+      |                |                      |
     | mascota+      |                |                      |
     | servicios     |                |                      |
     |─────────────→|                 |                      |
     |               |──registrar()──→|                      |
     |               |                |──existeDuplicado()──→|
     |               |                |                      |──false
     |               |                |←─────────────────────|
     |               |                |──guardar(atencion)──→|
     |               |                |←──atencionId─────────|
     |               |                |
     |               |          FacturaController ──→ FacturaRepository
     |               |                |──generarFactura()──→ FacturaCtrl
     |               |                                       ──guardar()
     |               |←── factura (numero + total) ──────────|
     |←─ muestra     |
     |   comprobante |
```

---

## 📝 Licencia

Proyecto académico — Universidad Tecnológica del Perú 2026-I.  
No se permite uso comercial sin autorización.
