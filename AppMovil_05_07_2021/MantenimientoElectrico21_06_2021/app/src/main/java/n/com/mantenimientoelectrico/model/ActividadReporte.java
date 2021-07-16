package n.com.mantenimientoelectrico.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActividadReporte {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("cumplimiento")
    @Expose
    private String cumplimiento;

    @SerializedName("observacion")
    @Expose
    private String observacion;


    @SerializedName("permitivo")
    @Expose
    private String permitivo;

    @SerializedName("usuarioact")
    @Expose
    private String usuarioact;

    @SerializedName("usuariomod")
    @Expose
    private String usuariomod;

    @SerializedName("numeroAct")
    @Expose
    private long numeroAct;

    @SerializedName("fcreacion")
    @Expose
    private String fcreacion;

    @SerializedName("fmodificacion")
    @Expose
    private String fmodificacion;

    @SerializedName("equipo")
    @Expose
    private  Equipo equipo;

    @SerializedName("equipogeneral")
    @Expose
    private  EquipoGeneralReporte equipoGeneral;

    public ActividadReporte() {
    }

    public EquipoGeneralReporte getEquipoGeneral() {
        if(equipoGeneral==null)
            equipoGeneral=new EquipoGeneralReporte();
        return equipoGeneral;
    }

    public void setEquipoGeneral(EquipoGeneralReporte equipoGeneral) {
        this.equipoGeneral = equipoGeneral;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(String cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPermitivo() {
        return permitivo;
    }

    public void setPermitivo(String permitivo) {
        this.permitivo = permitivo;
    }

    public String getUsuarioact() {
        return usuarioact;
    }

    public void setUsuarioact(String usuarioact) {
        this.usuarioact = usuarioact;
    }

    public String getUsuariomod() {
        return usuariomod;
    }

    public void setUsuariomod(String usuariomod) {
        this.usuariomod = usuariomod;
    }

    public long getNumeroAct() {
        return numeroAct;
    }

    public void setNumeroAct(long numeroAct) {
        this.numeroAct = numeroAct;
    }

    public String getFcreacion() {
        return fcreacion;
    }

    public void setFcreacion(String fcreacion) {
        this.fcreacion = fcreacion;
    }

    public String getFmodificacion() {
        return fmodificacion;
    }

    public void setFmodificacion(String fmodificacion) {
        this.fmodificacion = fmodificacion;
    }

    public Equipo getEquipo() {
        if(equipo==null)
            equipo=new Equipo();
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
