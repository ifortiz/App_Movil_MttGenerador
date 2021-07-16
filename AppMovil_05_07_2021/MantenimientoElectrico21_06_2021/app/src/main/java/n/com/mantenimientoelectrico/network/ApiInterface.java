package n.com.mantenimientoelectrico.network;

import java.util.List;

import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.DetalleActividad;
import n.com.mantenimientoelectrico.model.EmailActividades;
import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.model.EquipoGeneral;
import n.com.mantenimientoelectrico.model.EquipoPrueba;
import n.com.mantenimientoelectrico.model.Reporte;
import n.com.mantenimientoelectrico.model.Unidad;
import n.com.mantenimientoelectrico.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface
{
/*UNIDAD*/
    @GET("/unidad/listar")
    Call<List<Unidad>> getListarUnidad();


/*ACTIVIDAD*/
    @Headers("Content-Type:application/json")
    @GET("actividad/actividadequipo/{id}")
    Call<Actividad> getActividadCurrent(@Path("id") Long id);

    @Headers("Content-Type:application/json")
    @POST("actividad/actividad")
    Call<Actividad> saveActividad(@Body Actividad actividad);

    @Headers("Content-Type:application/json")
    @POST("actividad/actividad/detalleactividad")
    Call<Actividad> saveActividadWithDetalleActividad(@Body Actividad actividad);


    @Headers("Content-Type:application/json")
    @PUT("equipogeneral/updatereporte")
    Call<Actividad> saveReporte(@Body Reporte reporte);

    @Headers("Content-Type:application/json")
    @GET("actividad/listarActividad/{id}")
    Call<List<Actividad>> llistarActividadesByEquipo(@Path("id") Long id);



    @Headers("Content-Type:application/json")
    @GET("actividad/listarActividadot/{ot}")
    Call<List<Actividad>> listarActividadesByOt(@Path("ot") Long ot);

    @Headers("Content-Type:application/json")
    @POST("actividad/email")
    Call<String> generarReporteActividades(@Body EmailActividades email);

    /*DETALLE ACTIVIDAD*/
    @Headers("Content-Type:application/json")
    @POST("detalleactividad/detalleactividad")
    Call<String> saveDetalleActividad(@Body DetalleActividad detalleActividad);

    @Headers("Content-Type:application/json")
    @POST("detalleactividad/detalleListActividad")
    Call<String> saveListDetalleActividad(@Body List<DetalleActividad> detalleActividad);

/*EQUIPO*/
    @GET("equipo/equipo/{id}")
    Call<Equipo> checkEquipoExits(@Path("id") Long id);

    @GET("equipogeneral/equipogeneralot/{ot}")
    Call<Actividad> getEquipoGeneralByOt(@Path("ot") Long ot);

    @GET("equipo/listar")
    Call<List<Equipo>> getListarEquipos();

    @GET("equipo/listar-motores")
    Call<List<Equipo>> getListarEquiposMotor();
    /*EQUIPO PRUEBA*/
    @GET("/equipoprueba/listar")
    Call<List<EquipoPrueba>> getListarEquiposPruebas();

/*EQUIPO GENERAL*/
    @POST("equipogeneral/equipogeneral")
    Call<String> saveEquipoGeneral(
            @Body EquipoGeneral equipoGeneral
    );

    @GET("equipogeneral/equipogeneralid/{id}")
    Call<EquipoGeneral> getEquipoGeneralById(@Path("id") Long id);


    @GET("equipogeneral/equipogeneralbyot/{ot}")
    Call<EquipoGeneral> getIdEquipogeneralByOt(@Path("ot") Long ot);


    @GET("equipogeneral/listar")
    Call<List<EquipoGeneral>> listarEquipoGeneral();

/*USUARIO*/
    @POST("/usuario/login")
    Call<Usuario> loginWithEmail(
            @Body Usuario usuario
    );

    @Headers("Content-Type:application/json")
    @POST("usuario/usuario")
    Call<Usuario> saveUser(
            @Body Usuario usuario
    );

    @GET("/usuario/listar")
    Call<List<Usuario>> getListarUsuario();

    @GET("usuario/usuario/{id}")
    Call<Usuario> getUserById(@Path("id") Long id);


}
