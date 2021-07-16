package n.com.mantenimientoelectrico.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.Utils.Util;
import n.com.mantenimientoelectrico.Utils.Validator;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.DetalleActividad;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterEquipoPrueba;
import n.com.mantenimientoelectrico.listener.spinners.EquipoPruebaListener;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoPruebaModel;


public class anillos_rozantes_actividad_4 extends Fragment  implements EquipoPruebaListener{

    EditText edit_valorPrueba;
    EditText edit_respuestaNo;
    TextView txt_nombreActividad;
    TextView txt_riesgoActividad;
    TextView txt_observacionesActividad;
    TextView txt_numeroActividad;
    Spinner spinner_equipos;
    RadioGroup rg_1;
    Actividad actividad;
    DetalleActividad detalleActividad;
    Button btn_guardardetalleactividad;
    Button btnSugerencias;
    SharedPreferences preferences;
    private boolean isSave=false;
    long idEquipoGeneral;
    private ActividadModel actividadModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.anillos_rozantes_actividad_4, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnAnillosSiguienteAct4 = view.findViewById(R.id.btnAnillosSiguienteAct4);
        Button btnAnillosAtrasAct4 = view.findViewById(R.id.btnAnillosAtrasAct4);
        Button btnValidar=view.findViewById(R.id.btn_validar_act4);
        btnSugerencias=view.findViewById(R.id.btn_sugerencias_act4);

        edit_valorPrueba=view.findViewById(R.id.editText);
        txt_nombreActividad=view.findViewById(R.id.textDescricionActividad1);
        txt_riesgoActividad=view.findViewById(R.id.txtAnillosDeRiesgosAct4);
        txt_numeroActividad=view.findViewById(R.id.numeroDeActividad);
        txt_observacionesActividad=view.findViewById(R.id.txtAnillosDeObservacionesAct4);
        edit_respuestaNo=view.findViewById(R.id.editext_no_cumpleAct6);
        rg_1=view.findViewById(R.id.rg_1);
        spinner_equipos=view.findViewById(R.id.spinner1);
        btn_guardardetalleactividad=view.findViewById(R.id.btn_guardar_act4);

        detalleActividad=new DetalleActividad();
        actividad=new Actividad();
        btn_guardardetalleactividad.setOnClickListener(v -> verificarDetalleActividad() );
        btnAnillosSiguienteAct4.setOnClickListener(v -> guardarAll());
        btnAnillosAtrasAct4.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.anillos_rozantes_actividad_3));
        btnValidar.setOnClickListener(v->validar());
        setupListenerRadiobuttons(rg_1,R.id.checkBoxNoAct4,edit_respuestaNo);

        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);
        idEquipoGeneral = preferences.getLong(getString(R.string.preferences_id_equipogeneral),0);
        btnSugerencias.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.sugerenciasFragment));

        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(), actividad -> {
            if ( actividad != null ){
                Toast.makeText(getContext(),"Se ha registrado",Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.anillos_rozantes_actividad_5);
            } else
                Toast.makeText(getContext(),"Lo sentimos, no se ha podido guardar",Toast.LENGTH_LONG).show();
        });

        EquipoPruebaModel equipoPruebaModel =  new ViewModelProvider(this).get(EquipoPruebaModel.class);
        equipoPruebaModel.getListEquipoPruebaObserver().observe(getViewLifecycleOwner(),equipoPruebas -> {
            if (equipoPruebas != null){
                SpinnerAdapterEquipoPrueba adapterEquipo=new SpinnerAdapterEquipoPrueba(requireContext(),R.layout.adapter_dropdown_item,equipoPruebas,this);
                spinner_equipos.setAdapter(adapterEquipo);
            }
        });
        equipoPruebaModel.getListEquipoPrueba();
    }

    private void validar() {
        if(!edit_valorPrueba.getText().toString().trim().isEmpty()) {

            double dato = Double.parseDouble(edit_valorPrueba.getText().toString().trim());
            if (dato < 600) {
                Util.showAlertDialogWithOnlyOk("Error", "Aislamiento muy bajo, revisar sugerencias",requireContext());
                btnSugerencias.setVisibility(View.VISIBLE);
            } else {
                Util.showAlertDialogWithOnlyOk("Correcto", "Aislamiento correcto",requireContext());
            }
        }
    }

    private void guardarAll() {
        if(!isSave){
            Toast.makeText(getContext(),"Debe de guardar antes",Toast.LENGTH_LONG).show();
        }else {
            String cumplimiento = Validator.validarRadiobuttons(rg_1, R.id.id_rb_no, edit_respuestaNo,requireContext());
            if(!cumplimiento.trim().isEmpty())
                guardarActividad(cumplimiento);
        }
    }

    private void guardarActividad(String cumplimiento) {
        String nocumple=edit_respuestaNo.getText().toString().trim();
        String observacion=txt_observacionesActividad.getText().toString().trim();
        long numeroAct=Long.parseLong(txt_numeroActividad.getText().toString().trim());
        long idUserResponsable=preferences.getLong(getString(R.string.preferences_id_responsable),5);
        long idUserCurrentModifico=preferences.getLong(getString(R.string.preferences_iduser),0);
        long idcodigo=preferences.getLong(getString(R.string.preferences_id_equipo),0);
        long numeroEquipoId=preferences.getLong(getString(R.string.preferences_id_equipo),0);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().getTime());

        actividad.getEquipo().setIdcod(idcodigo);
        actividad.getEquipoGeneral().setId(idEquipoGeneral);
        actividad.setDescripcion(txt_nombreActividad.getText().toString().trim());
        actividad.setCumplimiento(cumplimiento);
        actividad.setFmodificacion(timeStamp);
        actividad.setFcreacion(timeStamp);
        actividad.setUsuarioact(idUserResponsable+"");
        actividad.setUsuariomod(idUserCurrentModifico+"");
        actividad.setNumeroAct(numeroAct);
        actividad.setPermitivo(nocumple);
        actividad.setObservacion(observacion);
        actividad.getEquipo().setIdcod(numeroEquipoId);

        List<DetalleActividad>detalleActividads=new ArrayList<>();
        detalleActividads.add(detalleActividad);
        actividad.setDetalleActividad(detalleActividads);
        actividadModel.saveActividadWithDetalleActividad(actividad);
    }


    private void setupListenerRadiobuttons(RadioGroup radioGroup,int id_no_r,EditText editText){
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==id_no_r)
                editText.setVisibility(View.VISIBLE);
            else{
                editText.setVisibility(View.GONE);
                editText.setText("");
            }
        });
    }
    private void verificarDetalleActividad() {
        String valorprueba=edit_valorPrueba.getText().toString().trim();
        String nombre=txt_nombreActividad.getText().toString().trim();
        if(valorprueba.isEmpty() || nombre.isEmpty()){
            Toast.makeText(getContext(),"NO PUEDE DEJAR CAMPOS VACIOS",Toast.LENGTH_LONG).show();
        }else{
            Log.d("descripcion",nombre);
            actividad.setDescripcion(nombre);
            detalleActividad.setNombre(nombre);
            detalleActividad.setValorPrueba( requireContext().getString(R.string.detalleactividad_unidad_medida,
                    valorprueba,"MΩ"));
            isSave=true;
            Util.showAlertDialogWithOnlyOk("Actividad guardada","La actividad ha sido guardada satisfactoriamente",requireContext());
        }
    }

    @Override
    public void onEquipoSelected(long id) {
        actividad.getEquipoPrueba().setId(id);
    }
}