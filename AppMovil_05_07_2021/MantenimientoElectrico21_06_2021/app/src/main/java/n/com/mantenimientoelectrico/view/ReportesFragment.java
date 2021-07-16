package n.com.mantenimientoelectrico.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterEquipoPrueba;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.model.EquipoGeneral;
import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.network.APIClient;
import n.com.mantenimientoelectrico.adapter.RecyclerEquipoAdapter;
import n.com.mantenimientoelectrico.adapter.RecyclerEquipoGeneralAdapter;
import n.com.mantenimientoelectrico.adapter.SpinneerAdapterEquipoGeneral;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterReporteEquipo;
import n.com.mantenimientoelectrico.viewmodel.EquipoPruebaModel;
import n.com.mantenimientoelectrico.viewmodel.ListEquipoGeneralModel;
import n.com.mantenimientoelectrico.viewmodel.ListEquipoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportesFragment extends Fragment  {

    AutoCompleteTextView spinner;
    RadioGroup rg_1;

    Usuario usuarioCurrent;
    SharedPreferences preferences;
    Button btn_find;
    RecyclerView recyclerView;
    TextView txt_dataEquipo;
    List<Actividad> listActividades=null;

    ListEquipoGeneralModel listEquipoGeneralModel;
    ListEquipoModel listEquipoModel;
    public ReportesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reportes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rg_1=view.findViewById(R.id.rg_1);
        spinner=view.findViewById(R.id.spinner1);
        btn_find=view.findViewById(R.id.btn_find);
        txt_dataEquipo=view.findViewById(R.id.txt_dataequipo);
         recyclerView=view.findViewById(R.id.recyclerview);
        setupListenerRadiobuttons(rg_1);
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        btn_find.setOnClickListener(v-> find());
        getInfoUserCurrent();

        listEquipoGeneralModel = new ViewModelProvider(this).get(ListEquipoGeneralModel.class);
        listEquipoGeneralModel.getListEquipoGeneralObserver().observe(getViewLifecycleOwner(),listEquipoGeneral -> {
            if ( listEquipoGeneral != null ){
                SpinneerAdapterEquipoGeneral adapterEquipo=new SpinneerAdapterEquipoGeneral(requireContext(),listEquipoGeneral);
                spinner.setAdapter(adapterEquipo);
                spinner.requestFocus();
                spinner.showDropDown();
            }
        });

        listEquipoModel = new ViewModelProvider(this).get(ListEquipoModel.class);
        listEquipoModel.getListEquipoObserver().observe(getViewLifecycleOwner(),equipos -> {
            if ( equipos != null ){
                SpinnerAdapterReporteEquipo adapterEquipo=new SpinnerAdapterReporteEquipo(requireContext(),equipos);
                spinner.setAdapter(adapterEquipo);
                spinner.requestFocus();
                spinner.showDropDown();
            }
        });
    }

    private void find() {
        if(listActividades!=null)
            listActividades.clear();
        int valido=validarRadiobuttons(rg_1,R.id.findByOt);
        if(valido ==0  || spinner.getText().toString().trim().isEmpty()){
            Toast.makeText(getContext(),"PORFAVOR SELECCIONE UN ITEM A BUSCAR",Toast.LENGTH_LONG).show();
        } else{
            if(valido==1 ){
                long ot=Long.parseLong(spinner.getText().toString().trim());
                getActividadesByOt(ot);

            }else{
                //equipo
                generateFindByEquipo();
            }
        }
    }



    private void getActividadesByOt(long idEquipoGeneral){
        APIClient.getClient().createServiceApi().listarActividadesByOt(idEquipoGeneral).enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(response.isSuccessful() && response.body()!=null  && response.body().size()>0){
                    RecyclerEquipoGeneralAdapter adapter =new RecyclerEquipoGeneralAdapter(response.body(),requireContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    txt_dataEquipo.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);

                }else{
                    Toast.makeText(getContext(),"No hemos encontrado ninguna actividad",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {

            }
        });
    }
    private void generateFindByEquipo(){
        listActividades=new ArrayList<>();
        long equipoId=Long.parseLong(spinner.getText().toString().trim());
        APIClient.getClient().createServiceApi().llistarActividadesByEquipo(equipoId).enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(response.body()!=null && response.isSuccessful() && response.body().size()>0){
                    long otCurrent=0L;
                    for(int i=0; i<response.body().size();i++){
                        if(otCurrent!=response.body().get(i).getEquipoGeneral().getOt()){
                            listActividades.add(response.body().get(i));
                        }
                        otCurrent=response.body().get(i).getEquipoGeneral().getOt();


                    }

                RecyclerEquipoAdapter adapter =new RecyclerEquipoAdapter(listActividades,requireContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    Equipo equipo=response.body().get(0).getEquipo();
                    txt_dataEquipo.setText(getString(R.string.recycler_dataequipo,equipo.getDescripcion(),equipo.getCorrientenom(),equipo.getSerie(),equipo.getModelo(),equipo.getPotencia()));
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);

                }else{
                    Toast.makeText(getContext(),"No hemos encontrado ningun ot asociado",Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {

            }
        });
    }
    private int validarRadiobuttons(RadioGroup radioGroup, int ot) {
        if(radioGroup.getCheckedRadioButtonId() == -1)
            return 0;
        else {
            if(radioGroup.getCheckedRadioButtonId()==ot){
                //ot
                return 1;
            }else{//equipo
                return 2;
            }

        }
    }

    private void setupListenerRadiobuttons(RadioGroup radioGroup){
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.findByOt)
                loadEquipoGeneralSpinner();
            else
                loadEquipoSpinner();

        });
    }

    private void loadEquipoGeneralSpinner() {
        spinner.setText("");
        listEquipoGeneralModel.getListEquipoGeneral();
    }

    private void loadEquipoSpinner( ) {
        spinner.setText("");
        listEquipoModel.getListEquipo();
    }

    private void getInfoUserCurrent(){
        long id_to_emai_current=preferences.getLong(getString(R.string.preferences_iduser),0);
        APIClient.getClient().createServiceApi().getUserById(id_to_emai_current).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful() && response.body()!=null)
                    usuarioCurrent=response.body();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }





}