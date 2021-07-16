package n.com.mantenimientoelectrico.view;

import android.content.Context;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.adapter.SpinnerAdapterSupervisor;
import n.com.mantenimientoelectrico.listener.spinners.SupervisorListener;
import n.com.mantenimientoelectrico.model.EmailActividades;
import n.com.mantenimientoelectrico.model.Reporte;
import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.viewmodel.ActividadModel;
import n.com.mantenimientoelectrico.viewmodel.ResponsableUsuarioModel;
import n.com.mantenimientoelectrico.viewmodel.SimpleResponseModel;
import n.com.mantenimientoelectrico.viewmodel.UsuarioModel;


public class trafo_distribucion_exci_reporte extends Fragment implements  SupervisorListener{

    EditText edit_reporteCambios;
    EditText edit_reporteCondicion;
    EditText edit_reportePendientes;
    SharedPreferences preferences;
    Spinner spinerSupervisor;
    Usuario usuarioResponsable;
    Usuario usuarioCurrent;
    private String correoSupervisor="";
    private UsuarioModel usuarioModel;
    private ResponsableUsuarioModel responsableModel;
    private SimpleResponseModel simpleResponseModel;
    private ActividadModel actividadModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.trafo_distribucion_exci_reporte, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_finalizar = view.findViewById(R.id.btn_finalizar);
        Button btn_atras = view.findViewById(R.id.btn_atras);
        Button btn_guardar = view.findViewById(R.id.btn_GuardarReporte);

        edit_reporteCambios=view.findViewById(R.id.editextCambios);
        edit_reporteCondicion=view.findViewById(R.id.editextCondicion);
        edit_reportePendientes=view.findViewById(R.id.editextPendientes);
        spinerSupervisor = view.findViewById(R.id.spinnerSupervisor);
        btn_finalizar.setOnClickListener(v -> generarReporteByOt());
        btn_guardar.setOnClickListener(v->{
            long ot= preferences.getLong(requireContext().getString(R.string.preferences_ot),0);
            Reporte reporte=new Reporte();
            reporte.setCondicion(edit_reporteCondicion.getText().toString().trim());
            reporte.setModificacion(edit_reporteCambios.getText().toString().trim());
            reporte.setPendiente(edit_reportePendientes.getText().toString().trim());
            reporte.setId_ot(ot);
            actividadModel.saveReporte(reporte);
        });
        btn_atras.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.trafo_distribucion_exci_act_8));
        preferences=requireActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        responsableModel = new ViewModelProvider(this).get(ResponsableUsuarioModel.class);
        responsableModel.getUsuarioResponsableObserver().observe(getViewLifecycleOwner(),responsable ->{
            if ( responsable!= null)
                usuarioResponsable= responsable;
        });
        long id_to_emai_responsable=preferences.getLong(getString(R.string.preferences_id_responsable),0);
        responsableModel.getInfoUserResponsable(id_to_emai_responsable);

        loadSupervisor(this);

        actividadModel = new ViewModelProvider(this).get(ActividadModel.class);
        actividadModel.getActividadObser().observe(getViewLifecycleOwner(),actividad ->{
            if (actividadModel != null)
                Toast.makeText(getContext(),"Se ha guardado el reporte",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getContext(),"Error no se guardo el reporte",Toast.LENGTH_LONG).show();
        });
        /*USUARIO MODEL*/
        usuarioModel = new ViewModelProvider(this).get(UsuarioModel.class);
        usuarioModel.getUsuariobserver().observe(getViewLifecycleOwner(), current -> {
            if(current != null)
                usuarioCurrent = current;
        });
        long id_to_emai_current=preferences.getLong(getString(R.string.preferences_iduser),0);
        usuarioModel.getInfoUserCurrent(id_to_emai_current);

        simpleResponseModel = new ViewModelProvider(this).get(SimpleResponseModel.class);
        simpleResponseModel.getSimpleResponseObserve().observe(getViewLifecycleOwner(),s -> {
            if( s!=null){
                Toast.makeText(getContext(), "Email ha sido enviado", Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).navigate(R.id.trafo_excitacion_equipos);
            }else
                Toast.makeText(getContext(), "no se ha podido enviar el correo", Toast.LENGTH_LONG).show();
        });

    }

    private void loadSupervisor(SupervisorListener listener){
        List<String> listEmail = List.of(" juan.alvarez@celec.gob.ec","juan.bernalm@celec.gob.ec");
        SpinnerAdapterSupervisor adapterSpinner = new SpinnerAdapterSupervisor(requireContext(),R.layout.adapter_dropdown_item,listEmail,listener);
        spinerSupervisor.setAdapter(adapterSpinner);
    }

    private void generarReporteByOt() {
        if (  ! correoSupervisor.trim().isEmpty()) {
            /* data to send*/
            long _idEquipo = preferences.getLong(getString(R.string.preferences_id_equipo), 0);
            long _ot = preferences.getLong(getString(R.string.preferences_ot), 0);
            String _nombreEquipo = preferences.getString(getString(R.string.preferences_nombreEquipo), "-1");

            String _modificaciones = edit_reporteCambios.getText().toString().trim();
            String _condicionObjeto = edit_reporteCondicion.getText().toString().trim();
            String _pendientes = edit_reportePendientes.getText().toString().trim();

            EmailActividades email = new EmailActividades();
            email.setCondiciones(_condicionObjeto);
            email.setModificaciones(_modificaciones);
            email.setPendientes(_pendientes);
            email.setCorreoCurrent(usuarioCurrent.getCorreo());
            email.setCorreoResponsable(usuarioResponsable.getCorreo());
            email.setNombreCompletoResponsable(usuarioResponsable.getNombre() + " " + usuarioResponsable.getApellido());
            email.setNombreCompletoEquipo(_idEquipo + "-" + _nombreEquipo);
            email.setCorreoSupervisor(correoSupervisor);
            email.setOt(_ot);
            simpleResponseModel.generarReporteActividades(email);
        }else{
            Toast.makeText(getContext(),"Necesita un supervisor para finalizar el reporte",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSupervisorSelected(String supervisor) {
        correoSupervisor = supervisor;
    }
}