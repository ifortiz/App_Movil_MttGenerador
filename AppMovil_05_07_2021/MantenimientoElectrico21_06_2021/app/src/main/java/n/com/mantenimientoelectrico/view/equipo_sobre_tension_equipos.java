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


public class equipo_sobre_tension_equipos extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.equipo_sobre_tension_equipos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_Descargador = view.findViewById(R.id.btn_Descargador);
        Button btn_TransformadorPot = view.findViewById(R.id.btn_TransformadorPot);
        Button btn_Capacitor = view.findViewById(R.id.btn_Capacitor);
        Button btn_FinalizarSobretension = view.findViewById(R.id.btn_FinalizarSobretension);
        Button btn_AtrasSobretensionEquipos = view.findViewById(R.id.btn_AtrasSobretensionEquipos);



        btn_Descargador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.descargador_ingresar_datos);
            }
        });
        btn_TransformadorPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.trafo_potencial_ingresar_datos);
            }
        });
        btn_Capacitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.capacitor_ingresar_datos);
            }
        });
        btn_FinalizarSobretension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas);
            }
        });
        btn_AtrasSobretensionEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.equipo_sobre_tension_reglas_de_oro);
            }
        });


    }
}