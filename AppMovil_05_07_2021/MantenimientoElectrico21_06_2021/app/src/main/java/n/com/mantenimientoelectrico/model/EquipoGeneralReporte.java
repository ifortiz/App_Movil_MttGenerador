package n.com.mantenimientoelectrico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EquipoGeneralReporte {
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

    @SerializedName("actividad")
    @Expose
    private List<Actividad> Actividad;

}
