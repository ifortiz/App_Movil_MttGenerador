package n.com.mantenimientoelectrico.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.Utils.Util;
import n.com.mantenimientoelectrico.Utils.Validator;
import n.com.mantenimientoelectrico.adapter.RecyclerAdapterTermico;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterEquipoPrueba;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterReporteEquipo;
import n.com.mantenimientoelectrico.listener.spinners.EquipoPruebaListener;
import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.model.DetalleActividad;
import n.com.mantenimientoelectrico.model.Tablero;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoPruebaModel;
import n.com.mantenimientoelectrico.viewmodel.ListEquipoModel;

public class tab_control_termico_act_7 extends Fragment implements EquipoPruebaListener {
    private List<Tablero> listTableros ;
    private RecyclerAdapterTermico adapterTermico;
    private Actividad actividad;
    private ActividadModel actividadModel;
    private SharedPreferences preferences;
    private EditText edit_nocumple;
    private TextView txt_observaciones,txt_numeroActividad,txt_descripcion;
    private static int numActividad=7;
    private RadioGroup rg_1;
    private String cumplimiento="";
    private long idEquipoGeneral;
    private ListEquipoModel listEquipoModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_control_termico_act_7, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguiente = view.findViewById(R.id.btn_siguiente);
        Button btn_atras = view.findViewById(R.id.btn_atras);
        Button btn_nuevoTermico = view.findViewById(R.id.btn_nuevoTermico);
        RecyclerView recyclerView =  view.findViewById(R.id.recyclerTablero);
        Spinner spinnerEquipo = view.findViewById(R.id.spinnerEquipoMed);
        edit_nocumple = view.findViewById(R.id.editext_no_cumpleAct);
        txt_observaciones = view.findViewById(R.id.textDeObservaciones);
        txt_numeroActividad  = view.findViewById(R.id.txtNumDeAct);
        txt_descripcion = view.findViewById(R.id.txtDeDescricion);
        rg_1=view.findViewById(R.id.rg_id_1);
        listTableros = new ArrayList<>();
        actividad=new Actividad();
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);


        btn_nuevoTermico.setOnClickListener(v-> addTermico() );
        btn_siguiente.setOnClickListener(v -> verificarActividad());
        btn_atras.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.tab_control_termico_act_6));

        EquipoPruebaModel equipoPruebaModel = new ViewModelProvider(this).get(EquipoPruebaModel.class);
        equipoPruebaModel.getListEquipoPruebaObserver().observe(getViewLifecycleOwner(),equipoPruebas -> {
            if ( equipoPruebas == null)
                Toast.makeText(getContext(),"No tiene Equipos de pruebas ",Toast.LENGTH_LONG).show();
            else{
                SpinnerAdapterEquipoPrueba adapterEquipo=new SpinnerAdapterEquipoPrueba(requireContext(),R.layout.adapter_dropdown_item,equipoPruebas, this);
                spinnerEquipo.setAdapter(adapterEquipo);
            }
        });
        equipoPruebaModel.getListEquipoPrueba();

        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(), actividad -> {
            if ( actividad == null )
                Toast.makeText(getContext(),"Lo sentimos ha ocurrido un error ",Toast.LENGTH_LONG).show();
            else{
                preferences.edit().putInt(requireContext().getString(R.string.preferences_actividad_saved),numActividad).apply();
                Toast.makeText(getContext(),"Se ha registrado todo",Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.tab_control_termico_act_8);
            }
        });

        listEquipoModel = new ViewModelProvider(this).get(ListEquipoModel.class);
        listEquipoModel.getListEquipoObserver().observe(getViewLifecycleOwner(),equipos -> {
            if ( equipos != null ){
                adapterTermico = new RecyclerAdapterTermico(listTableros,equipos,requireContext());
                addTermico();
                recyclerView.setAdapter(adapterTermico);
            }
        });
        listEquipoModel.getListEquipoMotores();

        idEquipoGeneral = preferences.getLong(getString(R.string.preferences_id_equipogeneral),0);

    }
    private void addTermico(){
        listTableros.add(new Tablero());
        adapterTermico.notifyItemInserted(listTableros.size()-1);
    }


    private void verificarActividad(){
         cumplimiento =  Validator.validarRadiobuttons(rg_1, R.id.checkBox1, edit_nocumple,requireContext());
        if(!cumplimiento.trim().isEmpty())
            getDatos();
        else
            Toast.makeText(getContext(),"No puede dejar campos vacios",Toast.LENGTH_LONG).show();
    }
    private void getDatos(){
       listTableros = adapterTermico.getItems();
       int  contadorErros = 0;
        for (Tablero tablero: listTableros) {
            if (!tablero.isSave())
               contadorErros++;
        }
        if( contadorErros == 0 )
            guardarDetalleActividad(listTableros);
        else
            Util.showAlertDialogWithOnlyOk("Actividades sin guardar","Tiene "+contadorErros+" Actividades sin guardar,Porfavor Complete sus campos",requireContext());
    }

    private void guardarDetalleActividad(List<Tablero> listTableros){

        String nocumple=edit_nocumple.getText().toString().trim();
        String observacion=txt_observaciones.getText().toString().trim();
        String descripcion= txt_descripcion.getText().toString().trim();
        long numeroAct=Long.parseLong(txt_numeroActividad.getText().toString().trim());
        long idUserResponsable=preferences.getLong(getString(R.string.preferences_id_responsable),5);
        long idUserCurrentModifico=preferences.getLong(getString(R.string.preferences_iduser),0);
        long idcodigo=preferences.getLong(getString(R.string.preferences_id_equipo),0);

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Calendar.getInstance().getTime());

        actividad.getEquipoGeneral().setId(idEquipoGeneral);
        actividad.getEquipo().setIdcod(idcodigo);
        actividad.setCumplimiento(cumplimiento);
        actividad.setFmodificacion(timeStamp);
        actividad.setFcreacion(timeStamp);
        actividad.setUsuarioact(idUserResponsable+"");
        actividad.setUsuariomod(idUserCurrentModifico+"");
        actividad.setNumeroAct(numeroAct);
        actividad.setPermitivo(nocumple);
        actividad.setDescripcion(descripcion);
        actividad.setObservacion(observacion);
        List<DetalleActividad> detalleActividads= new ArrayList<>();
        for (Tablero tablero : listTableros){
            detalleActividads.add(tablero.getIdMotor());
            detalleActividads.add(tablero.getMarca());
            detalleActividads.add(tablero.getRangoDesde());
            detalleActividads.add(tablero.getRangoHasta());
            detalleActividads.add(tablero.getCalActual());
            detalleActividads.add(tablero.getCalAnterior());
            detalleActividads.add(tablero.getVecesIr150());
            detalleActividads.add(tablero.getVecesIr200());
            detalleActividads.add(tablero.getTiempoDisparo150());
            detalleActividads.add(tablero.getTiempoDisparo200());
        }
        actividad.setDetalleActividad(detalleActividads);
        actividadModel.saveActividadWithDetalleActividad(actividad);
    }

    @Override
    public void onEquipoSelected(long id) {
        actividad.getEquipoPrueba().setId(id);
    }
}