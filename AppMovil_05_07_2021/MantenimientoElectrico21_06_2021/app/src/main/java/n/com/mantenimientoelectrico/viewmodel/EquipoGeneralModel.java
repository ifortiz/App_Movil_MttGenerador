package n.com.mantenimientoelectrico.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.model.EquipoGeneral;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipoGeneralModel extends ViewModel {
    private MutableLiveData<EquipoGeneral> equipoGeneral;

    public EquipoGeneralModel (){
        equipoGeneral = new MutableLiveData<>();
    }
    public MutableLiveData<EquipoGeneral> getEquipoGeneralObserver(){
        return equipoGeneral;
    }

    public void getEquipoGeneralByOt(long ot){
        APIClient.getClient().createServiceApi().getIdEquipogeneralByOt(ot).enqueue(new Callback<EquipoGeneral>() {
            @Override
            public void onResponse(Call<EquipoGeneral> call, Response<EquipoGeneral> response) {
                if(response.isSuccessful() && response.body() != null){
                    equipoGeneral.postValue(response.body());
                }
                else
                    equipoGeneral.postValue(null);
            }

            @Override
            public void onFailure(Call<EquipoGeneral> call, Throwable t) {
                equipoGeneral.postValue(null);
            }
        });
    }
}
