package n.com.mantenimientoelectrico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("password")
    @Expose
    private String contraseña;

    @SerializedName("apellido")
    @Expose
    private String apellido;

    @SerializedName("correo")
    @Expose
    private String correo;

    @SerializedName("id")
    @Expose
    private long id;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
