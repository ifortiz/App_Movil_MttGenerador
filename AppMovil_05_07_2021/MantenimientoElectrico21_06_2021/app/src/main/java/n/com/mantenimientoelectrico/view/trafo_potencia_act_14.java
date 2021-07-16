package n.com.mantenimientoelectrico.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.Utils.Util;
import n.com.mantenimientoelectrico.Utils.Validator;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoGeneralModel;


public class trafo_potencia_act_14 extends Fragment {

    EditText edit_noRespuesta;
    Actividad actividad;
    RadioGroup rg_1;
    TextView txt_nombreActividad;
    TextView txt_observaciones;
    TextView txt_numeroAct;
    SharedPreferences preferences;
    long  ot,idEquipoGeneral;
    private int numActividad=14;
    private ActividadModel actividadModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.trafo_potencia_act_14, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguiente1 = view.findViewById(R.id.btn_siguienteAct);
        Button btn_atras1 = view.findViewById(R.id.btn_atras);

        rg_1=view.findViewById(R.id.rg_id_1);
        edit_noRespuesta=view.findViewById(R.id.editext_no_cumpleAct);
        txt_nombreActividad=view.findViewById(R.id.textDescricionActividad);
        txt_observaciones=view.findViewById(R.id.textDeObservaciones);
        txt_numeroAct=view.findViewById(R.id.numeroDeActividad);
        actividad=new Actividad();
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);

        Util.setupListenerRadiobuttons(rg_1,R.id.checkBox1,edit_noRespuesta);
        btn_siguiente1.setOnClickListener(v -> verificarActividad());
        btn_atras1.setOnClickListener(v ->   Navigation.findNavController(v).navigate(R.id.trafo_potencia_act_13));

        ot=preferences.getLong(getString(R.string.preferences_ot),0);
        EquipoGeneralModel equipoGeneralModel = new ViewModelProvider( this).get(EquipoGeneralModel.class);
        equipoGeneralModel.getEquipoGeneralObserver().observe(getViewLifecycleOwner(), equipoGeneral -> {
            if (equipoGeneral != null) {
                idEquipoGeneral = equipoGeneral.getId();
                preferences.edit().putLong(getString(R.string.preferences_id_equipogeneral), idEquipoGeneral).apply();
            }
        });
        equipoGeneralModel.getEquipoGeneralByOt(ot);
        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(), actividad -> {
            if ( actividad != null ){
                preferences.edit().putInt(requireContext().getString(R.string.preferences_actividad_saved),numActividad).apply();
                Toast.makeText(getContext(),"la Actividad ha sido registrada",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigate(R.id.trafo_potencia_act_15);
            }
        });
    }

    private void verificarActividad(){
        String cumplimiento =  Validator.validarRadiobuttons(rg_1, R.id.checkBox1, edit_noRespuesta,requireContext());
        if(!cumplimiento.trim().isEmpty())
            guardarActividad(cumplimiento);
    }

    private void guardarActividad(String cumplimiento) {
        String nocumple=edit_noRespuesta.getText().toString().trim();
        String observacion=txt_observaciones.getText().toString().trim();
        long numeroAct=Long.parseLong(txt_numeroAct.getText().toString().trim());
        long numeroEquipoId=preferences.getLong(getString(R.string.preferences_id_equipo),0);
        long idUserResponsable=preferences.getLong(getString(R.string.preferences_id_responsable),5);
        long idUserCurrentModifico=preferences.getLong(getString(R.string.preferences_iduser),0);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        actividad.setDescripcion(txt_nombreActividad.getText().toString());
        actividad.getEquipoGeneral().setId(idEquipoGeneral);
        actividad.setCumplimiento(cumplimiento);
        actividad.setFmodificacion(timeStamp);
        actividad.setFcreacion(timeStamp);
        actividad.setUsuarioact(idUserResponsable+"");
        actividad.setUsuariomod(idUserCurrentModifico+"");
        actividad.setNumeroAct(numeroAct);
        actividad.setPermitivo(nocumple);
        actividad.setObservacion(observacion);
        actividad.getEquipo().setIdcod(numeroEquipoId);
        actividadModel.saveActividad(actividad);
    }
}