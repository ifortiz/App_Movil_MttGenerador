package n.com.mantenimientoelectrico.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleActividad {
    @SerializedName("nomprueba")
    @Expose
    private String nombre;

    @SerializedName("valorprueba")
    @Expose
    private String valorPrueba;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValorPrueba() {
        return valorPrueba;
    }

    public void setValorPrueba(String valorPrueba) {
        this.valorPrueba = valorPrueba;
    }


    public DetalleActividad(){}

    public DetalleActividad(long idActividad,String nombre,String valorPrueba){
        this.nombre=nombre;
        this.valorPrueba=valorPrueba;

    }

    public DetalleActividad(String nombre,String valorPrueba){
        this.nombre=nombre;
        this.valorPrueba=valorPrueba;

    }


}
