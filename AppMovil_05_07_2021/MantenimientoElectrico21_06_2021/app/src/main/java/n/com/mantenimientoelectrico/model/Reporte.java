package n.com.mantenimientoelectrico.model;

public class Reporte {

    private Long id;
    private String descripcion;
    private String modificacion;
    private String condicion;
    private String pendiente;
    private long id_ot;



    public Reporte() {
    }

    public long getId_ot() {
        return id_ot;
    }

    public void setId_ot(long id_ot) {
        this.id_ot = id_ot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModificacion() {
        return modificacion;
    }

    public void setModificacion(String modificacion) {
        this.modificacion = modificacion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }
}
