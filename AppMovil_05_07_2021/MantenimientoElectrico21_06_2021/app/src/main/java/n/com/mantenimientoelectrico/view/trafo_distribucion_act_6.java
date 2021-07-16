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
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterEquipoPrueba;
import n.com.mantenimientoelectrico.listener.spinners.EquipoPruebaListener;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.DetalleActividad;
import n.com.mantenimientoelectrico.model.UnidadesMedida;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoGeneralModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoPruebaModel;

public class trafo_distribucion_act_6 extends Fragment implements EquipoPruebaListener {

    EditText edit_noRespuesta;
    Actividad actividad;
    RadioGroup rg_1;
    TextView txt_nombreActividad;
    TextView txt_observaciones;
    TextView txt_numeroAct;
    SharedPreferences preferences;
    long  ot,idEquipoGeneral;
    private int numActividad=6;
    List<DetalleActividad> detalleActividades;
    private ActividadModel actividadModel;
    EditText valor_AT_TIERRA_30Seg;
    EditText valor_AT_TIERRA_1min;
    EditText valor_BT_TIERRA_30Seg;
    EditText valor_BT_TIERRA_1min;
    EditText valor_AT_BT_30Seg;
    EditText valor_AT_BT_1min;
    EditText valor_TempBobina;
    EditText valor_FaseC_TIERRA_30Seg;
    EditText valor_FaseC_TIERRA_1min;
    private static final String MINUTO_UNO="1 MINUTO";
    private static final String TREINTA_SEGUNDOS="30 SEGUNDOS";
    private static final String ALTA_TENSION = "ALTA TENSIÓN";
    private static final String BAJA_TENSION = "BAJA TENSIÓN";
    private static final String ALTA_Y_BAJA_TENSION = "ALTA TENSIÓN - BAJA TENSIÓN(MΩ)";
    private boolean isSave=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.trafo_distribucion_act_6, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguiente1 = view.findViewById(R.id.btn_siguiente);
        Button btn_atras1 = view.findViewById(R.id.btn_atras);
        Button btn_validar = view.findViewById(R.id.btn_validar_act5);
        Spinner spinner_equipos=view.findViewById(R.id.spinner1);
        valor_AT_TIERRA_30Seg = view.findViewById(R.id.valor_AT_TIERRA_30Seg);
        valor_AT_TIERRA_1min =  view.findViewById(R.id.valor_AT_TIERRA_1min);
        valor_BT_TIERRA_30Seg =  view.findViewById(R.id.valor_BT_TIERRA_30Seg);
        valor_BT_TIERRA_1min =  view.findViewById(R.id.valor_BT_TIERRA_1min);
        valor_AT_BT_30Seg =  view.findViewById(R.id.valor_AT_BT_30Seg);
        valor_AT_BT_1min =  view.findViewById(R.id.valor_AT_BT_1min);
        valor_TempBobina= view.findViewById(R.id.valor_TempBobina);
         valor_FaseC_TIERRA_30Seg = view.findViewById(R.id.valor_FaseC_TIERRA_30Seg);
         valor_FaseC_TIERRA_1min = view.findViewById(R.id.valor_FaseC_TIERRA_1min);
        rg_1=view.findViewById(R.id.rg_id_1);
        edit_noRespuesta=view.findViewById(R.id.editext_no_cumpleAct);
        txt_nombreActividad=view.findViewById(R.id.txtDeDescricion);
        txt_observaciones=view.findViewById(R.id.textDeObservaciones);
        txt_numeroAct=view.findViewById(R.id.txtNumDeAct);
        actividad=new Actividad();
        detalleActividades=new ArrayList<>();
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);

        Util.setupListenerRadiobuttons(rg_1,R.id.checkBox1,edit_noRespuesta);
        btn_validar.setOnClickListener(v -> {
            validarDetalleActividad();
        });
        btn_siguiente1.setOnClickListener(v -> verificarActividad());
        btn_atras1.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.trafo_distribucion_act_5));

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
                Toast.makeText(getContext(),"la Actividad ha sido registrada",Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.trafo_distribucion_act_7);
            }
        });

        EquipoPruebaModel equipoPruebaModel = new ViewModelProvider(this).get(EquipoPruebaModel.class);
        equipoPruebaModel.getListEquipoPruebaObserver().observe(getViewLifecycleOwner(),equipoPruebas -> {
            if ( equipoPruebas == null)
                Toast.makeText(getContext(),"No tiene Equipos de pruebas ",Toast.LENGTH_LONG).show();
            else{
                SpinnerAdapterEquipoPrueba adapterEquipo=new SpinnerAdapterEquipoPrueba(requireContext(),R.layout.adapter_dropdown_item,equipoPruebas,this);
                spinner_equipos.setAdapter(adapterEquipo);
            }
        });
        equipoPruebaModel.getListEquipoPrueba();
    }

    private void validarDetalleActividad() {
        String valor1 = valor_AT_BT_1min.getText().toString();
        String valor2 = valor_AT_BT_30Seg.getText().toString();
        String valor3 = valor_BT_TIERRA_1min.getText().toString();
        String valor4 = valor_BT_TIERRA_30Seg.getText().toString();
        String valor5 = valor_AT_TIERRA_1min.getText().toString();
        String valor6 = valor_AT_TIERRA_30Seg.getText().toString();
        if (valor1 .trim().isEmpty() || valor2 .trim().isEmpty() || valor3 .trim().isEmpty() || valor4 .trim().isEmpty()
        || valor5 .trim().isEmpty()  ||valor6 .trim().isEmpty() || valor_TempBobina.getText().toString().trim().isEmpty() ){
            Toast.makeText(getContext(),"Ha dejado campos vacios",Toast.LENGTH_SHORT).show();
        }else {
            isSave = true;
            detalleActividades.add(new DetalleActividad(ALTA_Y_BAJA_TENSION+MINUTO_UNO,
                    requireContext().getString(R.string.detalleactividad_unidad_medida,valor1, UnidadesMedida.Amperios)));
            detalleActividades.add(new DetalleActividad(ALTA_Y_BAJA_TENSION+TREINTA_SEGUNDOS,
                    requireContext().getString(R.string.detalleactividad_unidad_medida,valor2, UnidadesMedida.Amperios)));
            detalleActividades.add(new DetalleActividad(BAJA_TENSION+MINUTO_UNO,
                    requireContext().getString(R.string.detalleactividad_unidad_medida,valor3, UnidadesMedida.Amperios)));
            detalleActividades.add(new DetalleActividad(BAJA_TENSION+TREINTA_SEGUNDOS,
                    requireContext().getString(R.string.detalleactividad_unidad_medida,valor4, UnidadesMedida.Amperios)));
            detalleActividades.add(new DetalleActividad(ALTA_TENSION+MINUTO_UNO,
                    requireContext().getString(R.string.detalleactividad_unidad_medida,valor5, UnidadesMedida.Amperios)));
            detalleActividades.add(new DetalleActividad(ALTA_TENSION+TREINTA_SEGUNDOS,
                    requireContext().getString(R.string.detalleactividad_unidad_medida,valor6, UnidadesMedida.Amperios)));
            detalleActividades.add(new DetalleActividad("Temperatura de la bobina (°C)",valor_TempBobina.getText().toString().trim()));
            detalleActividades.add(new DetalleActividad("Voltaje aplicado(VDC)  AT",valor_FaseC_TIERRA_1min.getText().toString().trim()));
            detalleActividades.add(new DetalleActividad("Voltaje aplicado(VDC)  BT",valor_FaseC_TIERRA_30Seg.getText().toString().trim()));
            Util.showAlertDialogWithOnlyOk("Actividades validades","Las actividades han sido validadas",requireContext());
        }

    }

    private void verificarActividad(){
        String cumplimiento =  Validator.validarRadiobuttons(rg_1, R.id.checkBox1, edit_noRespuesta,requireContext());
        if(!cumplimiento.trim().isEmpty())
            guardarActividad(cumplimiento);
    }

    private void guardarActividad(String cumplimiento) {
        if (isSave) {
            String nocumple = edit_noRespuesta.getText().toString().trim();
            String observacion = txt_observaciones.getText().toString().trim();
            long numeroAct = Long.parseLong(txt_numeroAct.getText().toString().trim());
            long numeroEquipoId = preferences.getLong(getString(R.string.preferences_id_equipo), 0);
            long idUserResponsable = preferences.getLong(getString(R.string.preferences_id_responsable), 5);
            long idUserCurrentModifico = preferences.getLong(getString(R.string.preferences_iduser), 0);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().getTime());
            actividad.setDescripcion(txt_nombreActividad.getText().toString());
            actividad.getEquipoGeneral().setId(idEquipoGeneral);
            actividad.setCumplimiento(cumplimiento);
            actividad.setFmodificacion(timeStamp);
            actividad.setFcreacion(timeStamp);
            actividad.setUsuarioact(idUserResponsable + "");
            actividad.setUsuariomod(idUserCurrentModifico + "");
            actividad.setNumeroAct(numeroAct);
            actividad.setPermitivo(nocumple);
            actividad.setObservacion(observacion);
            actividad.getEquipo().setIdcod(numeroEquipoId);
            actividad.setDetalleActividad(detalleActividades);
            actividadModel.saveActividadWithDetalleActividad(actividad);
        }else
            Toast.makeText(getContext(),"No ha validado antes",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEquipoSelected(long id) {
        actividad.getEquipoPrueba().setId(id);
    }
}