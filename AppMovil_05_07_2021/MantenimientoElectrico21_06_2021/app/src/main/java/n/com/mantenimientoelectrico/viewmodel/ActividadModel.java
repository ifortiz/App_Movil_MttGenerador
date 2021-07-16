package n.com.mantenimientoelectrico.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.Reporte;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadModel extends ViewModel {
   private MutableLiveData<Actividad> actividadRespuesta;

   public ActividadModel(){
       actividadRespuesta = new MutableLiveData<>();
   }

   public MutableLiveData<Actividad> getActividadObser(){
       return actividadRespuesta;
   }

   public void saveActividad ( Actividad actividad){
       APIClient.getClient().createServiceApi().saveActividad(actividad).enqueue(new Callback<Actividad>() {
           @Override
           public void onResponse(Call<Actividad> call, Response<Actividad> response) {
               if(response.isSuccessful() && response.body()!=null ){
                  actividadRespuesta.postValue(response.body());
               }else{
                  actividadRespuesta.postValue(null);
               }
           }

           @Override
           public void onFailure(Call<Actividad> call, Throwable t) {
               if(t != null)
                   Log.d("error",t.getMessage());
           }
       });
   }

   public void  saveReporte(Reporte reporte){
       APIClient.getClient().createServiceApi().saveReporte(reporte).enqueue(new Callback<Actividad>() {
           @Override
           public void onResponse(Call<Actividad> call, Response<Actividad> response) {
               if(response.isSuccessful() && response.body() != null ){
                    actividadRespuesta.postValue(response.body());
               }else
                   actividadRespuesta.postValue(null);
           }
           @Override
           public void onFailure(Call<Actividad> call, Throwable t) {

           }
       });
   }

   public void saveActividadWithDetalleActividad(Actividad actividad){
       APIClient.getClient().createServiceApi().saveActividadWithDetalleActividad(actividad).enqueue(new Callback<Actividad>() {
           @Override
           public void onResponse(Call<Actividad> call, Response<Actividad> response) {
               if( response.isSuccessful())
                 actividadRespuesta.postValue(response.body());
               else
                   actividadRespuesta.postValue(null);
           }

           @Override
           public void onFailure(Call<Actividad> call, Throwable t) {
               if(t != null )
                   actividadRespuesta.postValue(null);
           }
       });
   }

    //metodo para verificar si existe el numero de ot
    public void getActividadByOt(long ot) {
        APIClient.getClient().createServiceApi().getEquipoGeneralByOt(ot).enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                if(response.isSuccessful() && response.body()!=null){
                    actividadRespuesta.postValue(response.body());
                }else
                    actividadRespuesta.postValue(null);
            }

            @Override
            public void onFailure(Call<Actividad> call, Throwable t) {
                actividadRespuesta.postValue(null);
            }
        });
    }
}
