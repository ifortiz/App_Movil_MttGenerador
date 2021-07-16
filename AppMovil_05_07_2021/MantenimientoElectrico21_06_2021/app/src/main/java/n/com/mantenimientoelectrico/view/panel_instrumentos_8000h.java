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


public class panel_instrumentos_8000h extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.panel_instrumentos_8000h, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_PanInstru8000h = view.findViewById(R.id.btn_PanInstru8000h);
        Button btn_AtrasPanInstru8000h = view.findViewById(R.id.btn_AtrasPanInstru8000h);


        btn_PanInstru8000h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.panel_instrumentos_menu);
            }
        });
        btn_AtrasPanInstru8000h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas);
            }
        });

    }
}