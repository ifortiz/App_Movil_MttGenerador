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


public class anillos_rozantes_actividad_3 extends Fragment {

    EditText edit_noRespuesta;
    Actividad actividad;
    RadioGroup rg_4,rg_3,rg_2,rg_1;
    TextView txt_nombreActividad;
    TextView txt_observaciones;
    TextView txt_numeroAct;
    SharedPreferences preferences;
    long idEquipoGeneral;

    private boolean isSave=false;
    private ActividadModel actividadModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.anillos_rozantes_actividad_3, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguiente1 = view.findViewById(R.id.btn_siguiente1);
        Button btn_atras1 = view.findViewById(R.id.btn_atras1);
        Button btn_validar= view.findViewById(R.id.btn_guardar_act3);


        btn_siguiente1.setOnClickListener(v ->verificarActividad() );
        btn_atras1.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.anillos_rozantes_actividad_1));
        btn_validar.setOnClickListener(v->verificarDetalleActividad());

        rg_1=view.findViewById(R.id.rg_1);
        rg_2=view.findViewById(R.id.rg_2);
        rg_3=view.findViewById(R.id.rg_3);
        rg_4=view.findViewById(R.id.rg_4);

        edit_noRespuesta=view.findViewById(R.id.editext_no_cumpleAct6);
        txt_nombreActividad=view.findViewById(R.id.textDescricionActividad1);
        txt_observaciones=view.findViewById(R.id.textDeObservaciones);
        txt_numeroAct=view.findViewById(R.id.numeroDeActividad);
        actividad=new Actividad();
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);

        setupListenerRadiobuttons(rg_4,R.id.checkBoxNo,edit_noRespuesta);

        idEquipoGeneral = preferences.getLong(getString(R.string.preferences_id_equipogeneral),0);

        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(), actividad -> {
            if ( actividad != null ){
                Toast.makeText(getContext(),"la Actividad ha sido registrada",Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.anillos_rozantes_actividad_4);
            }
        });
    }

    private void verificarDetalleActividad(){
        boolean valid_rg1=  validarRadiobuttonWithoutEditext(rg_1);
        boolean valid_rg2=  validarRadiobuttonWithoutEditext(rg_2);
        boolean valid_rg3=  validarRadiobuttonWithoutEditext(rg_3);
        if(valid_rg1 || valid_rg2 || valid_rg3){
            Toast.makeText(getContext(),"PORFAVOR SELECCIONE UNA OPCION",Toast.LENGTH_LONG).show();
        }else{
            isSave=true;
            Util.showAlertDialogWithOnlyOk("actvidad guardado","esta actividad ha sido guardada satisfactoriamente",requireContext());
        }
    }

    private boolean validarRadiobuttonWithoutEditext(RadioGroup rg){
        return  (rg.getCheckedRadioButtonId() == -1);
        }


    private void verificarActividad(){
        if(!isSave){
            Toast.makeText(getContext(),"DEBE DE GUARDAR ANTES DE CONTINUAR",Toast.LENGTH_LONG).show();
        }else {
            String cumplimiento = Validator.validarRadiobuttons(rg_4,R.id.checkBoxNo,edit_noRespuesta,requireContext());
            if(!cumplimiento.trim().isEmpty())
                guardarActividad(cumplimiento);
        }
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