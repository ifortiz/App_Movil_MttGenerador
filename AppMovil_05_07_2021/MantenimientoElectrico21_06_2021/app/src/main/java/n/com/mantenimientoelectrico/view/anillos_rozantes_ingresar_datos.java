package n.com.mantenimientoelectrico.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.model.EquipoGeneral;
import n.com.mantenimientoelectrico.model.Unidad;
import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.network.APIClient;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterUnidad;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterUsuarios;
import n.com.mantenimientoelectrico.listener.spinners.SpinnersActions;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoModel;
import n.com.mantenimientoelectrico.viewmodel.ListUnidadModel;
import n.com.mantenimientoelectrico.viewmodel.ListUsuarioModel;
import n.com.mantenimientoelectrico.viewmodel.SimpleResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class anillos_rozantes_ingresar_datos extends Fragment  implements SpinnersActions {

    Spinner _spinnerUnidad;
    Spinner _spinnerUsuarios;
    EditText edit_numeroOt;
    EditText codigoEquipo;
    ConstraintLayout bodyview;
    Button btn_guardar2;
    Button btn_guardar;
    EquipoGeneral equipoGeneral;
    SharedPreferences preferences;
    private boolean condition1 = false;
    private static final long NO_ACTIVIDAD_CREATED = -1;
    private static final long NO_OT_FOUND = 5000;
    private static final long MAX_NUMERO_ACTIVIDAD_OT = 8;
    private EquipoModel equipoModel;
    private SimpleResponseModel simpleResponseModel;
    private ActividadModel actividadModel;
    private ListUsuarioModel listUsuarioModel;
    private ListUnidadModel listUnidadModel;
    private long codigo=0;
    private long  ot=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.anillos_rozantes_ingresar_datos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguiente = view.findViewById(R.id.btn_siguiente);
        Button btn_atras = view.findViewById(R.id.btn_atras);
        btn_guardar=view.findViewById(R.id.btn_Guardar);
        btn_guardar2=view.findViewById(R.id.btn_Guardar2);

        _spinnerUsuarios=view.findViewById(R.id.spinner1);
        _spinnerUnidad=view.findViewById(R.id.spinnerUnidad);
        edit_numeroOt=view.findViewById(R.id.editTextNumOT);
        codigoEquipo=view.findViewById(R.id.editTextCodEquipo);
        bodyview=view.findViewById(R.id.bodysection);
        edit_numeroOt.setEnabled(true);
        equipoGeneral=new EquipoGeneral();
        btn_guardar.setOnClickListener(v-> verificarDatos() );

        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);

        btn_siguiente.setOnClickListener(v ->guardarDatos());

        btn_atras.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_equipos));

        equipoModel =  new ViewModelProvider(this).get(EquipoModel.class);
        equipoModel.getEquipoObserver().observe(getViewLifecycleOwner(), equipo -> {
            if ( equipo != null){
                preferences.edit().putLong(getString(R.string.preferences_id_equipo),codigo).apply();
                preferences.edit().putString(getString(R.string.preferences_nombreEquipo),equipo.getDescripcion()).apply();
                equipoGeneral.setIdEquipo(codigo);
                simpleResponseModel.saveEquipoGeneral(equipoGeneral);
            }else {
                Toast.makeText(getContext(), "Código de equipo no existe", Toast.LENGTH_LONG).show();
            }
        });

        simpleResponseModel =  new ViewModelProvider(this).get(SimpleResponseModel.class);
        simpleResponseModel.getSimpleResponseObserve().observe(getViewLifecycleOwner(), response -> {
            if ( response != null){
                Toast.makeText(getContext(), "Se ha registrado los datos corretamente", Toast.LENGTH_SHORT).show();
                navigate(R.id.anillos_rozantes_actividad_2);
            }else
                Toast.makeText(getContext(), "ha ocurrido un error inesperado", Toast.LENGTH_LONG).show();
        });

        actividadModel = new ViewModelProvider( this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(),actividad -> {
            if( actividad != null){
                preferences.edit().putLong(getString(R.string.preferences_ot),ot).apply();
                verificarStatusActividad(actividad,this);
            }else
                Toast.makeText(getContext(), "ha ocurrido un error inesperado", Toast.LENGTH_LONG).show();
        });

        listUsuarioModel = new ViewModelProvider(this).get(ListUsuarioModel.class);
        listUsuarioModel.getListUsuarioObserve().observe(getViewLifecycleOwner(),usuarios -> {
            if ( usuarios != null){
                SpinnerAdapterUsuarios spinnerAdapterUsuarios =new SpinnerAdapterUsuarios(requireContext(),R.layout.adapter_dropdown_item,usuarios,this);
                _spinnerUsuarios.setAdapter(spinnerAdapterUsuarios);
            }else
                Toast.makeText(getContext(), "Ningun usuario registrado", Toast.LENGTH_LONG).show();
        });

        listUnidadModel = new ViewModelProvider(this).get(ListUnidadModel.class);
        listUnidadModel.getListUnidadObserve().observe(getViewLifecycleOwner(),unidads -> {
            if ( unidads != null){
                SpinnerAdapterUnidad spinnerAdapterUnidad=new SpinnerAdapterUnidad(requireContext(),R.layout.adapter_dropdown_item,unidads,this);
                _spinnerUnidad.setAdapter(spinnerAdapterUnidad);
            }
        });
    }
    private void guardarDatos(){
        String data=codigoEquipo.getText().toString().trim();
        if(data.isEmpty()){
            Toast.makeText(getContext(),"Porfavor ingrese un CODIGO",Toast.LENGTH_LONG).show();
            return;
        }

        if(condition1) {
            codigo = Long.parseLong(data);
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
            String time = df.format(date);
            equipoGeneral.setFecha(time);
            equipoModel.checkEquipoExits(codigo);
        }else{
            Toast.makeText(getContext(), "Ingrese el código del equipo", Toast.LENGTH_LONG).show();
        }
    }

    private void verificarStatusActividad(Actividad actividad, SpinnersActions listener){
        if(actividad.getId()==NO_OT_FOUND){
            btn_guardar.setVisibility(View.GONE);
            btn_guardar.setEnabled(false);
            bodyview.setVisibility(View.VISIBLE);
            edit_numeroOt.setEnabled(false);
            listUsuarioModel.getListUsuarios();
            listUnidadModel.getListUnidad();
            Toast.makeText(getContext(),"NO EXISTE",Toast.LENGTH_LONG).show();
            condition1=true;
        }else{
            if(actividad.getId()==NO_ACTIVIDAD_CREATED){
                navigate(R.id.anillos_rozantes_actividad_2);
            }else{
                int numeroAct=(int)actividad.getNumeroAct();
                if(numeroAct>=MAX_NUMERO_ACTIVIDAD_OT){
                    Toast.makeText(getContext(),"ESTA OT YA TIENE  TODAS LAS ACTIVIDADES REALIZADAS",Toast.LENGTH_LONG).show();
                }else{
                    preferences.edit().putLong(getString(R.string.preferences_id_equipo),actividad.getEquipo().getIdcod()).apply();
                    preferences.edit().putLong(getString(R.string.preferences_id_responsable),actividad.getUsuario().getId()).apply();
                    numeroAct++;
                    switch (numeroAct) {
                        case 1:
                            navigate(R.id.anillos_rozantes_actividad_2);
                            break;
                        case 2:
                            navigate(R.id.anillos_rozantes_actividad_1);
                            break;
                        case 3:
                            navigate(R.id.anillos_rozantes_actividad_3);
                            break;
                        case 4:
                            navigate(R.id.anillos_rozantes_actividad_4);
                            break;
                        case 5:
                            navigate(R.id.anillos_rozantes_actividad_5);
                            break;
                        case 6:
                            navigate(R.id.anillos_rozantes_actividad_6);
                            break;
                        case 7:
                            navigate(R.id.anillos_rozantes_actividad_7);
                            break;
                        case 8:
                            navigate(R.id.anillos_rozantes_actividad_8);
                            break;
                        default:
                            navigate(R.id.anillos_rozantes_actividad_2);
                            break;
                    }
                }
            }
        }
    }
    private void verificarDatos( ) {
        String numeroOt=edit_numeroOt.getText().toString().trim();
        if(numeroOt.equals("")){
            Toast.makeText(getContext(),"Editext vacio",Toast.LENGTH_LONG).show();
            return;
        }
         ot=Long.parseLong(numeroOt);
        equipoGeneral.setOt(ot);
        actividadModel.getActividadByOt(ot);
    }

    private  void navigate(int destino){
        Navigation.findNavController(requireView()).navigate(destino);
    }

    @Override
    public void onUsuarioSelected(long id) {
        Log.d("usuario","usuario: "+id);
        equipoGeneral.getUsuario().setId(id);
        preferences.edit().putLong(getString(R.string.preferences_id_responsable),id).apply();

    }
    @Override
    public void onUnidadSelected(long id) {
       equipoGeneral.getUnidad().setId(id);
    }
}