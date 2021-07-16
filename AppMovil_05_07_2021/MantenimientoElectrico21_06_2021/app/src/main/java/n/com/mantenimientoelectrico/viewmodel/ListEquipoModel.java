package n.com.mantenimientoelectrico.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import n.com.mantenimientoelectrico.adapter.SpinnerAdapterReporteEquipo;
import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListEquipoModel extends ViewModel {
    private MutableLiveData<List<Equipo>> listEquipo;

    public ListEquipoModel (){
        listEquipo = new MutableLiveData<>();
    }

    public MutableLiveData<List<Equipo>> getListEquipoObserver(){
        return listEquipo;
    }

    public void getListEquipo(){
        APIClient.getClient().createServiceApi().getListarEquipos().enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                if(response.isSuccessful()  && response.body() !=null){
                    listEquipo.postValue(response.body());
                }else
                    listEquipo.postValue(null);
            }

            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                listEquipo.postValue(null);
            }
        });
    }
    public void getListEquipoMotores(){
        APIClient.getClient().createServiceApi().getListarEquiposMotor().enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                if(response.isSuccessful()  && response.body() !=null){
                    listEquipo.postValue(response.body());
                }else
                    listEquipo.postValue(null);
            }

            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                listEquipo.postValue(null);
            }
        });
    }
}
