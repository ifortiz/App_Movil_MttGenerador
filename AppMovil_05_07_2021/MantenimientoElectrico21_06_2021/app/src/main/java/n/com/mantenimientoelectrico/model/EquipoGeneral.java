package n.com.mantenimientoelectrico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class EquipoGeneral {
    @SerializedName("ot")
    @Expose
    private long ot;

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    @SerializedName("unidad")
    @Expose
    private Unidad unidad;

    private Long idEquipo;
    @SerializedName("reporte")
    @Expose
    private Reporte reporte;

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        if(usuario==null)
            usuario=new Usuario();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Unidad getUnidad() {
        if(unidad==null)
            unidad=new Unidad();
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public long getOt() {
        return ot;
    }

    public void setOt(long ot) {
        this.ot = ot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
