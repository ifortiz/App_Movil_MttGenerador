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
import n.com.mantenimientoelectrico.Utils.Validator;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;

public class anillos_rozantes_actividad_8 extends Fragment {

    EditText edit_noRespuesta;
    Actividad actividad;
    RadioGroup rg_1;
    TextView txt_nombreActividad;
    TextView txt_observaciones;
    TextView txt_numeroAct;
    SharedPreferences preferences;
    long idEquipoGeneral;
    private ActividadModel actividadModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.anillos_rozantes_actividad_8, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_siguienteAct8 = view.findViewById(R.id.btn_siguienteAct8);
        Button btn_atrasAct8 = view.findViewById(R.id.btn_atrasAct8);

        rg_1=view.findViewById(R.id.rg_id_1);
        edit_noRespuesta=view.findViewById(R.id.editext_no_cumpleAct6);
        txt_nombreActividad=view.findViewById(R.id.textDescricionActividad8);
        txt_observaciones=view.findViewById(R.id.textDeObservacionesActividad8);
        txt_numeroAct=view.findViewById(R.id.numeroDeActividad8);
        actividad=new Actividad();
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);
        setupListenerRadiobuttons(rg_1,R.id.checkBoxNoAct8,edit_noRespuesta);
        btn_siguienteAct8.setOnClickListener(v -> verificarActividad());
        btn_atrasAct8.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.anillos_rozantes_actividad_7));

        idEquipoGeneral = preferences.getLong(getString(R.string.preferences_id_equipogeneral),0);
        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(), actividad -> {
            if ( actividad != null ){
                Toast.makeText(getContext(),"la Actividad ha sido registrada",Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.anillos_rozantes_reporte);
            }
        });
    }

    private void verificarActividad(){
        String cumplimiento = Validator.validarRadiobuttons(rg_1,R.id.checkBoxNoAct8,edit_noRespuesta,requireContext());
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

        actividad.getEquipoGeneral().setId(idEquipoGeneral);
        actividad.setDescripcion(txt_nombreActividad.getText().toString());
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
    private void setupListenerRadiobuttons(RadioGroup radioGroup, int id_no_r, EditText editText){
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==id_no_r)
                editText.setVisibility(View.VISIBLE);
            else{
                editText.setVisibility(View.GONE);
                editText.setText("");
            }

        });
    }
}