package n.com.mantenimientoelectrico.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterUnidad;
import n.com.mantenimientoelectrico.model.Unidad;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUnidadModel   extends ViewModel {
    private MutableLiveData<List<Unidad>> listUnidad;

    public ListUnidadModel (){
        listUnidad = new MutableLiveData<>();
    }

    public MutableLiveData<List<Unidad>> getListUnidadObserve(){
        return listUnidad;
    }

    public void getListUnidad(){
        APIClient.getClient().createServiceApi().getListarUnidad().enqueue(new Callback<List<Unidad>>() {
            @Override
            public void onResponse(Call<List<Unidad>> call, Response<List<Unidad>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listUnidad.postValue(response.body());
                }else
                    listUnidad.postValue(null);
            }

            @Override
            public void onFailure(Call<List<Unidad>> call, Throwable t) {
                listUnidad.postValue(null);
            }
        });
    }
}