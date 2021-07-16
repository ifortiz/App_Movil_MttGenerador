package n.com.mantenimientoelectrico.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.model.Equipo;

import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipoModel extends ViewModel {
    private MutableLiveData<Equipo> equipo;

    public EquipoModel (){
        equipo = new MutableLiveData<>();
    }
    public MutableLiveData<Equipo> getEquipoObserver(){
        return equipo;
    }

    public void checkEquipoExits(long codigo){
        APIClient.getClient().createServiceApi().checkEquipoExits(codigo).enqueue(new Callback<Equipo>() {
            @Override
            public void onResponse(Call<Equipo> call, Response<Equipo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    equipo.postValue(response.body());
                } else {
                    equipo.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Equipo> call, Throwable t) {
                equipo.postValue(null);
            }
        });
    }
}