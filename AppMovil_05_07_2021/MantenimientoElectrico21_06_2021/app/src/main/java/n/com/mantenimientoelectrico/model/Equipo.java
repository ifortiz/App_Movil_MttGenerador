package n.com.mantenimientoelectrico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Equipo {
    @SerializedName("idcod")
    @Expose
    private long idcod;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("potencia")
    @Expose
    private String potencia;

    @SerializedName("modelo")
    @Expose
    private String modelo;

    @SerializedName("corrientenom")
    @Expose
    private String corrientenom;

    @SerializedName("serie")
    @Expose
    private String serie;

    @SerializedName("tipo")
    @Expose
    private String tipo;

    @SerializedName("voltaje")
    @Expose
    private String voltaje;

    @SerializedName("idEquipoGeneral")
    @Expose
    private long  idEquipoGeneral;


    public Equipo() {
    }

    public long getIdEquipoGeneral() {
        return idEquipoGeneral;
    }

    public void setIdEquipoGeneral(long idEquipoGeneral) {
        this.idEquipoGeneral = idEquipoGeneral;
    }

    public long getIdcod() {
        return idcod;
    }

    public void setIdcod(long idcod) {
        this.idcod = idcod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCorrientenom() {
        return corrientenom;
    }

    public void setCorrientenom(String corrientenom) {
        this.corrientenom = corrientenom;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(String voltaje) {
        this.voltaje = voltaje;
    }
}
