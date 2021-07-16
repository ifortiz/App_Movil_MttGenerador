package n.com.mantenimientoelectrico.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.model.EmailActividades;
import n.com.mantenimientoelectrico.model.EquipoGeneral;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpleResponseModel extends ViewModel {
    private MutableLiveData<String> simpleResponse;

    public SimpleResponseModel(){
        simpleResponse = new MutableLiveData<>();
    }
    public MutableLiveData<String> getSimpleResponseObserve(){
        return simpleResponse;
    }

    public void saveEquipoGeneral(EquipoGeneral equipoGeneral ){
        APIClient.getClient().createServiceApi().saveEquipoGeneral(equipoGeneral).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    simpleResponse.postValue(response.body());
                } else {
                    simpleResponse.postValue(null);
                }

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                simpleResponse.postValue(null);
            }
        });
    }

    public void generarReporteActividades(EmailActividades email ){
        APIClient.getClient().createServiceApi().generarReporteActividades(email).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body().equals("200")) {
                    simpleResponse.postValue(response.body());
                } else {
                    simpleResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                simpleResponse.postValue(null);
            }
        });
    }
}