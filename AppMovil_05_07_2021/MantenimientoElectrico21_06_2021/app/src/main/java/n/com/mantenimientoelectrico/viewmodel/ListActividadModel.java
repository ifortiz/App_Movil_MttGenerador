package n.com.mantenimientoelectrico.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.adapter.RecyclerEquipoAdapter;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActividadModel extends ViewModel {
    private MutableLiveData<List<Actividad>> listActividad;

    public ListActividadModel (){
        listActividad = new MutableLiveData<>();
    }

    public MutableLiveData<List<Actividad>> getListActividadObserver(){
        return listActividad;
    }

    public void getListEquipoGeneral(long equipoId){
        APIClient.getClient().createServiceApi().llistarActividadesByEquipo(equipoId).enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(response.body()!=null && response.isSuccessful() && response.body().size()>0){
                listActividad.postValue(response.body());
                }else{
                listActividad.postValue(null);
                }
            }


            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                listActividad.postValue(null);
            }
        });
    }
}
