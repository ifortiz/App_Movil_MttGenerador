package n.com.mantenimientoelectrico.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import n.com.mantenimientoelectrico.adapter.SpinneerAdapterEquipoGeneral;
import n.com.mantenimientoelectrico.model.EquipoGeneral;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEquipoGeneralModel  extends ViewModel {
    private MutableLiveData<List<EquipoGeneral>> listEquipoGeneral;

    public ListEquipoGeneralModel (){
        listEquipoGeneral = new MutableLiveData<>();
    }

    public MutableLiveData<List<EquipoGeneral>> getListEquipoGeneralObserver(){
        return listEquipoGeneral;
    }

    public void getListEquipoGeneral(){
        APIClient.getClient().createServiceApi().listarEquipoGeneral().enqueue(new Callback<List<EquipoGeneral>>() {
            @Override
            public void onResponse(Call<List<EquipoGeneral>> call, Response<List<EquipoGeneral>> response) {
                if(response.isSuccessful()  && response.body() !=null && response.body().size()>0){
                    listEquipoGeneral.postValue(response.body());
                }else
                    listEquipoGeneral.postValue(null);
            }

            @Override
            public void onFailure(Call<List<EquipoGeneral>> call, Throwable t) {
                listEquipoGeneral.postValue(null);
            }
        });
    }
}
