package n.com.mantenimientoelectrico.view;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.Utils.Util;
import n.com.mantenimientoelectrico.Utils.Validator;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.DetalleActividad;
import n.com.mantenimientoelectrico.model.UnidadesMedida;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterEquipoPrueba;
import n.com.mantenimientoelectrico.listener.spinners.EquipoPruebaListener;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoPruebaModel;

public class anillos_rozantes_actividad_5 extends Fragment implements EquipoPruebaListener{
    TextView txt_descripcionActividad;
    TextView txt_numActividad;
    TextView txt_Observaciones;

    TextView txt_horas_operacion;
    TextView txt_horas_proyeccion;
    TextView txt_escobillas_cambiadas;
    TextView txt_portaescobillas_cambiadas;

    EditText edit_horas_operacion;
    EditText edit_horas_proyeccion;
    EditText edit_escobillas_cambiadas;
    EditText edit_portaescobillas_cambiadas;

    Button btnSugerencias;
    Spinner spinner_equipos;
    RadioGroup rg_1;
    EditText act_inferior1, act_superior1,act_inferior2, act_superior2,act_inferior3, act_superior3;
    EditText act_inferior4, act_superior4,act_inferior5, act_superior5,act_inferior6, act_superior6;
    EditText act_inferior7, act_superior7,act_inferior8, act_superior8,act_inferior9, act_superior9;
    EditText act_inferior10, act_superior10,act_inferior11, act_superior11,act_inferior12, act_superior12;
    EditText act_inferior13, act_superior13,act_inferior14, act_superior14,act_inferior15, act_superior15;
    EditText act_inferior16, act_superior16,act_inferior17, act_superior17,act_inferior18, act_superior18;
    EditText act_inferior19, act_superior19,act_inferior20, act_superior20;
    EditText edit_nocumple;
    SharedPreferences preferences;
    Actividad actividad;
    List<DetalleActividad> detalleActividades;
    private boolean isSave=false;
    List<EditText> editTextList;
    long idEquipoGeneral;
    String descripcionAct;

    private ActividadModel actividadModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anillos_rozantes_actividad_5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view);
        Button btnAnillosSiguienteAct5 = view.findViewById(R.id.btnAnillosSiguienteAct5);
        Button btnAnillosAtrasAct5 = view.findViewById(R.id.btnAnillosAtrasAct5);
        Button btnAnillosValidar=view.findViewById(R.id.btn_validar_act5);
        Button btnGuardar= view.findViewById(R.id.btn_guardar_act5);
        btnSugerencias=view.findViewById(R.id.btn_sugerencias_act5);


        txt_descripcionActividad=view.findViewById(R.id.textDescricionActividad5);
        txt_numActividad=view.findViewById(R.id.numeroDeActividad);
        txt_Observaciones=view.findViewById(R.id.txtAnillosDeObservacionesAct5);

        txt_horas_operacion=view.findViewById(R.id.horasOp);
        txt_horas_proyeccion=view.findViewById(R.id.horasProyecion);
        txt_escobillas_cambiadas=view.findViewById(R.id.txtEscobillasCambiadas);
        txt_portaescobillas_cambiadas=view.findViewById(R.id.txtPortaEscobillasCambiadas);

        edit_horas_operacion=view.findViewById(R.id.editTextOrasOperacion);
        edit_horas_proyeccion=view.findViewById(R.id.editTextHorasProyeccion);
        edit_escobillas_cambiadas=view.findViewById(R.id.editTextEscobillasCambiadas);
        edit_portaescobillas_cambiadas=view.findViewById(R.id.editTextPortaEscobillasCambiadas);

        edit_nocumple=view.findViewById(R.id.editext_no_cumpleAct6);
        spinner_equipos=view.findViewById(R.id.spinner1);
        rg_1=view.findViewById(R.id.rg_1);
        detalleActividades=new ArrayList<>();
        actividad=new Actividad();

        btnAnillosAtrasAct5.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.anillos_rozantes_actividad_4));
        btnAnillosSiguienteAct5.setOnClickListener(v -> guardarAll());
        btnAnillosValidar.setOnClickListener(v-> validateBackgroundEditext());

        btnGuardar.setOnClickListener( v -> {
            if ( detalleActividades.size() == 0 )
                Util.showAlertDialogWithOnlyOk("Campos vacios","No puede guardar una actividad vacia",requireContext());
            else
                Util.showAlertDialogWithOnlyOk("Actividad guarda","La actividad ha sido guardada satisfactoriamente",requireContext());
        });

        setupListenerRadiobuttons(rg_1,R.id.checkBoxNoAct5,edit_nocumple);
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);

        idEquipoGeneral = preferences.getLong(getString(R.string.preferences_id_equipogeneral),0);
        btnSugerencias.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.sugerenciasFragment));

        descripcionAct=txt_descripcionActividad.getText().toString().trim();
        editTextList = Arrays.asList(act_inferior1, act_superior1,
                act_inferior2, act_superior2, act_inferior3, act_superior3, act_inferior4, act_superior4,
                act_inferior5, act_superior5,act_inferior6, act_superior6, act_inferior7, act_superior7,
                act_inferior8, act_superior8, act_inferior9, act_superior9, act_inferior10, act_superior10,
                act_inferior11, act_superior11, act_inferior12, act_superior12, act_inferior13, act_superior13, act_inferior14, act_superior14) ;
             //   act_inferior15, act_superior15,
             //   act_inferior16, act_superior16, act_inferior17, act_superior17, act_inferior18, act_superior18, act_inferior19, act_superior19, act_inferior20, act_superior20)

        /*equipo prueba viewmodel*/
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
        /*actividad view model*/
        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(), actividad -> {
            if ( actividad == null )
                Toast.makeText(getContext(),"Lo sentimos ha ocurrido un error ",Toast.LENGTH_LONG).show();
            else{
                isSave=true;
                Util.showAlertDialogWithOnlyOk("Datos registrados","Los datos han sido registrados con exito",requireContext());
                Navigation.findNavController(requireView()).navigate(R.id.anillos_rozantes_actividad_6);
            }
        });
    }


    private void validateBackgroundEditext() {
        if(detalleActividades.size()>0)
            detalleActividades.clear();
     try {
         boolean  errors=false;
         String prefijo;
         String characterSplit="@";
         int valor=1,indice=1;
         for(int i=0; i< editTextList.size(); i++){
             if(!editTextList.get(i).getText().toString().trim().isEmpty()){
                double valorDouble = Double.parseDouble(editTextList.get(i).getText().toString().trim());
                 if ( valorDouble < 30 || valorDouble > 64 ) {
                     errors=true;
                     editTextList.get(i).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.editext_error));
                 }else
                     editTextList.get(i).setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.editext_success));
                 prefijo=characterSplit;

                 if(indice%2==0) {
                     valor-=1;
                     prefijo += "SUP-" + valor;
                 }
                 else
                     prefijo+="INF-"+valor;

                 detalleActividades.add(new DetalleActividad(descripcionAct+prefijo,
                         requireContext().getString(R.string.detalleactividad_unidad_medida,
                                 editTextList.get(i).getText().toString().trim(), UnidadesMedida.milimetros)));
                 valor++;
                 indice++;
             }
         }

         if(errors)
             btnSugerencias.setVisibility(View.VISIBLE);
         showAlertDialogWithOnlyOk();
         isSave=true;
     }catch (NumberFormatException e){

         Log.d("validateditext","data"+e.getMessage());
        Toast.makeText(getContext(),"Ha ocurrido un error, porfavor no deje campos vacios",Toast.LENGTH_LONG).show();
     }
    }

    private void showAlertDialogWithOnlyOk(){
      Dialog dialog=new Dialog(requireContext());
      dialog.setContentView(R.layout.dialog_description_colores);
      dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
      dialog.setCancelable(true);
       TextView textViewOk=dialog.findViewById(R.id.clickok);
      dialog.show();
        textViewOk.setOnClickListener(v-> dialog.dismiss());
    }

    private void guardarAll() {
    String cumplimiento= Validator.validarRadiobuttons(rg_1,R.id.checkBoxNoAct5,edit_nocumple,requireContext());
        if(!cumplimiento.trim().isEmpty())
            guardarActividad(cumplimiento);
    }

    private void guardarActividad(String cumplimiento) {
        if(!isSave){
            Toast.makeText(getContext(),"Debe de guardar antes de continuar",Toast.LENGTH_LONG).show();
        }else{
        String nocumple=edit_nocumple.getText().toString().trim();
        String observacion=txt_Observaciones.getText().toString().trim();

        long numeroAct=Long.parseLong(txt_numActividad.getText().toString().trim());
        long idUserResponsable=preferences.getLong(getString(R.string.preferences_id_responsable),5);
        long idUserCurrentModifico=preferences.getLong(getString(R.string.preferences_iduser),0);
        long idcodigo=preferences.getLong(getString(R.string.preferences_id_equipo),0);
        long numeroEquipoId=preferences.getLong(getString(R.string.preferences_id_equipo),0);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().getTime());

        actividad.getEquipoGeneral().setId(idEquipoGeneral);
        actividad.getEquipo().setIdcod(idcodigo);
        actividad.setDescripcion(descripcionAct);
        actividad.setCumplimiento(cumplimiento);
        actividad.setFmodificacion(timeStamp);
        actividad.setFcreacion(timeStamp);
        actividad.setUsuarioact(idUserResponsable+"");
        actividad.setUsuariomod(idUserCurrentModifico+"");
        actividad.setNumeroAct(numeroAct);
        actividad.setPermitivo(nocumple);
        actividad.setObservacion(observacion);
        actividad.getEquipo().setIdcod(numeroEquipoId);

        if(!edit_horas_operacion.getText().toString().trim().isEmpty())
            detalleActividades.add(new DetalleActividad(txt_horas_operacion.getText().toString(), requireContext().getString(R.string.detalleactividad_unidad_medida,
                    edit_horas_operacion.getText().toString().trim(),"h")));
        if(!edit_horas_proyeccion.getText().toString().trim().isEmpty())
            detalleActividades.add(new DetalleActividad(txt_horas_proyeccion.getText().toString(), requireContext().getString(R.string.detalleactividad_unidad_medida,
                    edit_horas_proyeccion.getText().toString().trim(),"h")));
        if(!edit_escobillas_cambiadas.getText().toString().trim().isEmpty())
            detalleActividades.add(new DetalleActividad(txt_escobillas_cambiadas.getText().toString(),edit_escobillas_cambiadas.getText().toString()));
        if(!edit_portaescobillas_cambiadas.getText().toString().trim().isEmpty())
            detalleActividades.add(new DetalleActividad(txt_portaescobillas_cambiadas.getText().toString(),edit_portaescobillas_cambiadas.getText().toString()));

         actividad.setDetalleActividad(detalleActividades);
         actividadModel.saveActividadWithDetalleActividad(actividad);

        }
    }


    private void findView(View view) {

        act_inferior1=view.findViewById(R.id.inferior1);
        act_superior1=view.findViewById(R.id.superior1);
        act_inferior2=view.findViewById(R.id.inferior2);
        act_superior2=view.findViewById(R.id.superior2);
        act_inferior3=view.findViewById(R.id.inferior3);
        act_superior3=view.findViewById(R.id.superior3);
        act_inferior4=view.findViewById(R.id.inferior4);
        act_superior4=view.findViewById(R.id.superior4);
        act_inferior5=view.findViewById(R.id.inferior5);
        act_superior5=view.findViewById(R.id.superior5);
        act_inferior6=view.findViewById(R.id.inferior6);
        act_superior6=view.findViewById(R.id.superior6);
        act_inferior7=view.findViewById(R.id.inferior7);
        act_superior7=view.findViewById(R.id.superior7);
        act_inferior8=view.findViewById(R.id.inferior8);
        act_superior8=view.findViewById(R.id.superior8);
        act_inferior9=view.findViewById(R.id.inferior9);
        act_superior9=view.findViewById(R.id.superior9);
        act_inferior10=view.findViewById(R.id.inferior10);
        act_superior10=view.findViewById(R.id.superior10);
        act_inferior11=view.findViewById(R.id.inferior11);
        act_superior11=view.findViewById(R.id.superior11);
        act_inferior12=view.findViewById(R.id.inferior12);
        act_superior12=view.findViewById(R.id.superior12);
        act_inferior13=view.findViewById(R.id.inferior13);
        act_superior13=view.findViewById(R.id.superior13);
        act_inferior14=view.findViewById(R.id.inferior14);
        act_superior14=view.findViewById(R.id.superior14);
/*        act_inferior15=view.findViewById(R.id.inferior15);
        act_superior15=view.findViewById(R.id.superior15);
        act_inferior16=view.findViewById(R.id.inferior16);
        act_superior16=view.findViewById(R.id.superior16);
        act_inferior17=view.findViewById(R.id.inferior17);
        act_superior17=view.findViewById(R.id.superior17);
        act_inferior18=view.findViewById(R.id.inferior18);
        act_superior18=view.findViewById(R.id.superior18);
        act_inferior19=view.findViewById(R.id.inferior19);
        act_superior19=view.findViewById(R.id.superior19);
        act_inferior20=view.findViewById(R.id.inferior20);
        act_superior20=view.findViewById(R.id.superior20);*/
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
    @Override
    public void onEquipoSelected(long id) {
        actividad.getEquipoPrueba().setId(id);
    }
}