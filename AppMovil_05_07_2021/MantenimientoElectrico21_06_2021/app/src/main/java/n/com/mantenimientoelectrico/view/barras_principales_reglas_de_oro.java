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


public class barras_principales_reglas_de_oro extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.barras_principales_reglas_de_oro, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_SiguienteBarrasPrinReglasOro = view.findViewById(R.id.btn_SiguienteBarrasPrinReglasOro);
        Button btn_AtrasBarrasPrinReglasOro = view.findViewById(R.id.btn_AtrasBarrasPrinReglasOro);


        btn_SiguienteBarrasPrinReglasOro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.barras_principales_equipos);
            }
        });
        btn_AtrasBarrasPrinReglasOro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.barras_principales_menu);
            }
        });

    }
}