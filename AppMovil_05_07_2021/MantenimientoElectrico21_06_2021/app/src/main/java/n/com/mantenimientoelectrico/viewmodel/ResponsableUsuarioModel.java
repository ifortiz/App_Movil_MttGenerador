package n.com.mantenimientoelectrico.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponsableUsuarioModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;

    public ResponsableUsuarioModel(){
        usuario = new MutableLiveData<>();
    }
    public MutableLiveData<Usuario> getUsuarioResponsableObserver(){
        return usuario;
    }

    public void getInfoUserResponsable(long  id_to_emai_responsable){
        APIClient.getClient().createServiceApi().getUserById(id_to_emai_responsable).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    usuario.postValue(response.body());
                }else
                    usuario.postValue(null);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                usuario.postValue(null);
            }
        });
    }
}