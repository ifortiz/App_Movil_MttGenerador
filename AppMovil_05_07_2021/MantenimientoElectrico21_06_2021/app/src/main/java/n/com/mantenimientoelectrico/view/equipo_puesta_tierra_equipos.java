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


public class equipo_puesta_tierra_equipos extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.equipo_puesta_tierra_equipos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_Transformador = view.findViewById(R.id.btn_Transformador);
        Button btn_AtrasPuestaTierraEquipos = view.findViewById(R.id.btn_AtrasPuestaTierraEquipos);
        Button btn_FinalizarPuestaTierra = view.findViewById(R.id.btn_FinalizarPuestaTierra);

        btn_Transformador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.trafo_distribucion_ingresar_datos);
            }
        });
        btn_AtrasPuestaTierraEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.equipo_puesta_tierra_reglas_de_oro);
            }
        });
        btn_FinalizarPuestaTierra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas);
            }
        });

        

    }
}