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


public class alta_presion_equipos extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.alta_presion_equipos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_TabControl = view.findViewById(R.id.btn_TabControl);
        Button btn_Motor = view.findViewById(R.id.btn_Motor);
        Button btn_FinalizarAltaPres = view.findViewById(R.id.btn_FinalizarAltaPres);
        Button btn_AtrasAltaPresEquipos = view.findViewById(R.id.btn_AtrasAltaPresEquipos);


        btn_TabControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.tab_control_termico_ingresar_datos);
            }
        });
        btn_Motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.motor_ingresar_datos);
            }
        });
        btn_FinalizarAltaPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas);
            }
        });
        btn_AtrasAltaPresEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.alta_presion_reglas_de_oro);
            }
        });


    }

}