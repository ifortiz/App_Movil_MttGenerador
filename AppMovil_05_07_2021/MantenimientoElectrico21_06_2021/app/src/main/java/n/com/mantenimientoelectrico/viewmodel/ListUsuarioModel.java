package n.com.mantenimientoelectrico.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterUsuarios;
import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsuarioModel   extends ViewModel {
    private MutableLiveData<List<Usuario>> listUsuario;

    public ListUsuarioModel (){
        listUsuario = new MutableLiveData<>();
    }

    public MutableLiveData<List<Usuario>> getListUsuarioObserve(){
        return listUsuario;
    }

    public void getListUsuarios(){
        APIClient.getClient().createServiceApi().getListarUsuario().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful() && response.body() !=  null)
                {
                    listUsuario.postValue(response.body());
                }else
                    listUsuario.postValue(null);
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                listUsuario.postValue(null);
            }
        });
    }
}