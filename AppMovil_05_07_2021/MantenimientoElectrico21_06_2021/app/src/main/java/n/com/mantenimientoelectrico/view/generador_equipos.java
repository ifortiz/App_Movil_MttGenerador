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


public class generador_equipos extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.generador_equipos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_Estator = view.findViewById(R.id.btn_Estator);
        Button btn_Rotor = view.findViewById(R.id.btn_Rotor);
        Button btn_FinalizarGenerador = view.findViewById(R.id.btn_FinalizarGenerador);
        Button btn_AtrasGeneradorEquipos = view.findViewById(R.id.btn_AtrasGeneradorEquipos);

        btn_Estator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.estator_ingresar_datos);
            }
        });

        btn_Rotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.rotor_ingresar_datos);
            }
        });

        btn_FinalizarGenerador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas);
            }
        });
        btn_AtrasGeneradorEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.generador_reglas_de_oro);
            }
        });


    }
}