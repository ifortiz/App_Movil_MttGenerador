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


public class barras_principales_equipos extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.barras_principales_equipos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_BarrasPrin = view.findViewById(R.id.btn_BarrasPrin);
        Button btn_AtrasBarrasPrinEquipos = view.findViewById(R.id.btn_AtrasBarrasPrinEquipos);
        Button btn_FinalizarBarrasPrin = view.findViewById(R.id.btn_FinalizarBarrasPrin);


        btn_BarrasPrin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.barras_principales_ingresar_datos);
            }
        });
        btn_AtrasBarrasPrinEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.barras_principales_equipos);
            }
        });
        btn_FinalizarBarrasPrin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas);
            }
        });

    }
}