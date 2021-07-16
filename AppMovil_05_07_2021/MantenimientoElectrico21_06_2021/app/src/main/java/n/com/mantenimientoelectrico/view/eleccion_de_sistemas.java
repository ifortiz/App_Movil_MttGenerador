package n.com.mantenimientoelectrico.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import n.com.mantenimientoelectrico.R;


public class eleccion_de_sistemas extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.eleccion_de_sistemas, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_recinto_de_escobillas = view.findViewById(R.id.btn_recinto_de_escobillas);
        Button btn_generador = view.findViewById(R.id.btn_Generador);
        Button btn_EquipoPuestaTierra = view.findViewById(R.id.btn_EquipoPuestaTierra);
        Button btn_EquipoSobreTension = view.findViewById(R.id.btn_EquipoSobreTension);
        Button btn_BarrasPrincipales = view.findViewById(R.id.btn_BarrasPrincipales);
        Button btn_AltaPresion = view.findViewById(R.id.btn_AltaPresion);
        Button btn_ValvulaPricipal =view.findViewById(R.id.btn_ValvulaPricipal);
        Button btn_SistemaFrenado = view.findViewById(R.id.btn_SistemaFrenado);
        Button btn_TCU = view.findViewById(R.id.btn_TCU);
        Button btn_TrafoAuxiliares = view.findViewById(R.id.btn_TrafoAuxiliares);
        Button btn_AguaEnfriamiento = view.findViewById(R.id.btn_AguaEnfriamiento);
        Button btn_TrafoExcitacion = view.findViewById(R.id.btn_TrafoExcitacion);
        Button btn_Regulacion = view.findViewById(R.id.btn_Regulacion);
        Button btn_Ventilacion = view.findViewById(R.id.btn_Ventilacion);
        Button btn_PIT = view.findViewById(R.id.btn_PIT);
        Button btn_TrafoPrincipal = view.findViewById(R.id.btn_TrafoPrincipal);
        Button btn_atrasSistemas = view.findViewById(R.id.btn_atrasSistemas);

        btn_recinto_de_escobillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_8000h);
            }
        });
        btn_generador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.generador_8000h);
            }
        });
        btn_EquipoPuestaTierra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.equipo_puesta_tierra_8000h);
            }
        });
        btn_EquipoSobreTension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.equipo_sobre_tension_8000h);
            }
        });
        btn_BarrasPrincipales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.barras_principales_8000h);
            }
        });
        btn_AltaPresion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.alta_presion_8000h);
            }
        });
        btn_ValvulaPricipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.valvula_principal_8000h);
            }
        });
        btn_SistemaFrenado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.sistema_de_frenado_8000h);
            }
        });
        btn_TCU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.tablero_de_control_unidad_8000h);
            }
        });
        btn_TrafoAuxiliares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.trafo_auxiliares_8000h);
            }
        });
        btn_AguaEnfriamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.sis_agua_enfriamiento_8000h);
            }
        });
        btn_TrafoExcitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.trafo_excitacion_8000h);
            }
        });
        btn_Regulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.regulacion_8000h);
            }
        });
        btn_Ventilacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.ventilacion_8000h);
            }
        });
        btn_PIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.panel_instrumentos_8000h);
            }
        });
        btn_TrafoPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.transformador_principal_8000h);
            }
        });
        btn_atrasSistemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.menuSistemaReporteFragment);
            }
        });




    }
}