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


public class recinto_de_escobillas_aviso extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recinto_de_escobillas_aviso, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_aceptarRecintoEscobillas = view.findViewById(R.id.btn_aceptarRecintoEscobillas);

        btn_aceptarRecintoEscobillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_reglas_de_oro);
            }
        });


    }
}