package n.com.mantenimientoelectrico.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


import n.com.mantenimientoelectrico.model.EquipoPrueba;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipoPruebaModel extends ViewModel  {
    private  MutableLiveData<List<EquipoPrueba>> listEquipoPrueba;

    public EquipoPruebaModel (){
        listEquipoPrueba = new MutableLiveData<>();
    }
    public MutableLiveData<List<EquipoPrueba>> getListEquipoPruebaObserver(){
        return  listEquipoPrueba;
    }

    public void getListEquipoPrueba(){
        APIClient.getClient().createServiceApi().getListarEquiposPruebas().enqueue(new Callback<List<EquipoPrueba>>() {
            @Override
            public void onResponse(Call<List<EquipoPrueba>> call, Response<List<EquipoPrueba>> response) {
                if(response.isSuccessful() && response.body() !=null){
                    listEquipoPrueba.postValue(response.body());
                }else
                    listEquipoPrueba.postValue(null);
            }

            @Override
            public void onFailure(Call<List<EquipoPrueba>> call, Throwable t) {
                listEquipoPrueba.postValue(null);
            }
        });
    }
}
