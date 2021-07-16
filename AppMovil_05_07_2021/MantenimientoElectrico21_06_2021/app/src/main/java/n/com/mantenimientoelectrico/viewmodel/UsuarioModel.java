package n.com.mantenimientoelectrico.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;

    public UsuarioModel (){
        usuario = new MutableLiveData<>();
    }
    public MutableLiveData<Usuario> getUsuariobserver(){
        return usuario;
    }

    public void getInfoUserCurrent(long  id_to_emai_current){
        APIClient.getClient().createServiceApi().getUserById(id_to_emai_current).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful() && response.body()!=null)
                   usuario.postValue(response.body());
               else
                   usuario.postValue(null);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                usuario.postValue(null);
            }
        });
    }
}