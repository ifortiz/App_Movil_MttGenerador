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

public class panel_instrumentos_equipos extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.panel_instrumentos_equipos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_TabControl = view.findViewById(R.id.btn_TabControl);
        Button btn_FinalizarPIT = view.findViewById(R.id.btn_FinalizarPIT);
        Button btn_AtrasEquipos = view.findViewById(R.id.btn_AtrasEquipos);


        btn_TabControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.tab_control_senalizacion_ingresar_datos);
            }
        });
        btn_FinalizarPIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas);
            }
        });
        btn_AtrasEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.panel_instrumentos_reglas_de_oro);
            }
        });

    }
}