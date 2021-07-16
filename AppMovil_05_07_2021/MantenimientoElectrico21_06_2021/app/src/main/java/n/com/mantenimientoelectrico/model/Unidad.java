package n.com.mantenimientoelectrico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Unidad {
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("id")
    @Expose
    private  long id;


    public Unidad() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
