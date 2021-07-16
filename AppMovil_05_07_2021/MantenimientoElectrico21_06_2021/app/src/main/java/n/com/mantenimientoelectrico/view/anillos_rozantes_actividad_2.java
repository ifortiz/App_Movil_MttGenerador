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
import android.widget.LinearLayout;
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
import n.com.mantenimientoelectrico.model.UnidadesMedida;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterEquipoPrueba;
import n.com.mantenimientoelectrico.listener.spinners.EquipoPruebaListener;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoGeneralModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoPruebaModel;

public class anillos_rozantes_actividad_2 extends Fragment  implements EquipoPruebaListener {
    Spinner spinnerEquipo;
    DetalleActividad detalleActividad;
    Actividad actividad;
   Button btn_guardar_detalleactividad;
    TextView txt_nombreActividad;
    TextView txt_observaciones;
    TextView txt_numeroActividad;
    EditText edit_valorPrueba;
    EditText edit_nocumple;
    RadioGroup rg_1;
    SharedPreferences preferences;
    long ot,idEquipoGeneral;
    private boolean isSave=false;
    private int numActividad=1;
    LinearLayout viewActividad;
    //ViewModels
    private ActividadModel actividadModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anillos_rozantes_actividad_2, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguiente1 = view.findViewById(R.id.btn_siguiente1);
        Button btn_atras1 = view.findViewById(R.id.btn_atras1);
        btn_guardar_detalleactividad=view.findViewById(R.id.btn_guardar_act1);
        txt_nombreActividad=view.findViewById(R.id.textDescricionActividad1);
        txt_numeroActividad=view.findViewById(R.id.numeroDeActividad);
        txt_observaciones=view.findViewById(R.id.textView16);
        edit_valorPrueba=view.findViewById(R.id.editText4);
        spinnerEquipo=view.findViewById(R.id.spinner1);
        edit_nocumple=view.findViewById(R.id.editext_no_cumpleAct6);
        rg_1=view.findViewById(R.id.rg_1);
        viewActividad=view.findViewById(R.id.linearLayout1);

        setupListenerRadiobuttons(rg_1,R.id.id_rb_no,edit_nocumple);
        detalleActividad=new DetalleActividad();
        actividad=new Actividad();

        //guardar actividad
        btn_siguiente1.setOnClickListener(v ->guardarAll());
        btn_atras1.setOnClickListener(v-> Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_equipos));
        btn_guardar_detalleactividad.setOnClickListener(v->verificarDetalleActividad());
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name),0);
        ot=preferences.getLong(getString(R.string.preferences_ot),0);
        /*equipo general viewmodel*/
        EquipoGeneralModel equipoGeneralModel = new ViewModelProvider( this).get(EquipoGeneralModel.class);

        equipoGeneralModel.getEquipoGeneralObserver().observe(getViewLifecycleOwner(), equipoGeneral -> {
            if (equipoGeneral != null) {
                idEquipoGeneral = equipoGeneral.getId();
                preferences.edit().putLong(getString(R.string.preferences_id_equipogeneral), idEquipoGeneral).apply();
            }
        });
        equipoGeneralModel.getEquipoGeneralByOt(ot);
        /*equipo prueba viewmodel*/
        EquipoPruebaModel equipoPruebaModel = new  ViewModelProvider(this).get(EquipoPruebaModel.class);
        equipoPruebaModel.getListEquipoPruebaObserver().observe(getViewLifecycleOwner(),equipoPruebas -> {
            if ( equipoPruebas == null)
                Toast.makeText(getContext(),"No tiene Equipos de pruebas ",Toast.LENGTH_LONG).show();
            else{
                SpinnerAdapterEquipoPrueba adapterEquipo=new SpinnerAdapterEquipoPrueba(requireContext(),R.layout.adapter_dropdown_item,equipoPruebas, this);
                spinnerEquipo.setAdapter(adapterEquipo);
            }
        });
        equipoPruebaModel.getListEquipoPrueba();
        /*actividad view model*/
        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(), actividad -> {
            if ( actividad == null )
                Toast.makeText(getContext(),"Lo sentimos ha ocurrido un error ",Toast.LENGTH_LONG).show();
            else{
                preferences.edit().putInt(requireContext().getString(R.string.preferences_actividad_saved),numActividad).apply();
                Toast.makeText(getContext(),"Se ha registrado todo",Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.anillos_rozantes_actividad_1);
            }
        });

    }

    private void guardarAll() {
        if (!isSave) {
            Toast.makeText(getContext(), "Guarde los datos del aislamiento", Toast.LENGTH_LONG).show();
        } else {
            String cumplimiento = Validator.validarRadiobuttons(rg_1, R.id.id_rb_no, edit_nocumple,requireContext());
            if(!cumplimiento.trim().isEmpty())
                guardarActividad(cumplimiento);
        }

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

    private void guardarActividad(String cumplimiento) {
        String nocumple=edit_nocumple.getText().toString().trim();
        String observacion=txt_observaciones.getText().toString().trim();
        long numeroAct=Long.parseLong(txt_numeroActividad.getText().toString().trim());
        long idUserResponsable=preferences.getLong(getString(R.string.preferences_id_responsable),5);
        long idUserCurrentModifico=preferences.getLong(getString(R.string.preferences_iduser),0);
        long idcodigo=preferences.getLong(getString(R.string.preferences_id_equipo),0);
        long numeroEquipoId=preferences.getLong(getString(R.string.preferences_id_equipo),0);

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
        actividad.setObservacion(observacion);
        actividad.getEquipo().setIdcod(numeroEquipoId);
        List<DetalleActividad> detalleActividads= new ArrayList<>();
        detalleActividads.add(detalleActividad);
        actividad.setDetalleActividad(detalleActividads);
        actividadModel.saveActividadWithDetalleActividad(actividad);
    }


    private void verificarDetalleActividad() {
        String valorprueba=edit_valorPrueba.getText().toString().trim();
        String nombre=txt_nombreActividad.getText().toString().trim();
        if(valorprueba.isEmpty() || nombre.isEmpty()){
            Toast.makeText(getContext(),"Ingrese el valor del aislamiento",Toast.LENGTH_LONG).show();
        }else{
            if(!isSave) {
                Util.showAlertDialogWithOnlyOk("actividad guardada", "Ya ha guardado esta actividad",requireContext());
                actividad.setDescripcion(nombre);
                detalleActividad.setNombre(nombre);
                detalleActividad.setValorPrueba( requireContext().getString(R.string.detalleactividad_unidad_medida,
                        valorprueba, UnidadesMedida.megaHomnios));
                isSave=true;

            }else{
                Util.showAlertDialogWithOnlyOk("actividad guardada", "Ya ha guardado esta actividad",requireContext());
            }
        }
    }

    @Override
    public void onEquipoSelected(long id) {
    actividad.getEquipoPrueba().setId(id);

    }
}