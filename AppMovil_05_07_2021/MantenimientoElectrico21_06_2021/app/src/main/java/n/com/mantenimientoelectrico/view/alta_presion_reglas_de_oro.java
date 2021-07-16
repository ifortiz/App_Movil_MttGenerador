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


public class alta_presion_reglas_de_oro extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.alta_presion_reglas_de_oro, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_SiguienteAltaPresReglasOro = view.findViewById(R.id.btn_SiguienteAltaPresReglasOro);
        Button btn_AtrasAltaPresReglasOro = view.findViewById(R.id.btn_AtrasAltaPresReglasOro);


        btn_SiguienteAltaPresReglasOro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.alta_presion_equipos);
            }
        });
        btn_AtrasAltaPresReglasOro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.alta_presion_menu);
            }
        });


    }
}