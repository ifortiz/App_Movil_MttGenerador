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


public class rotor_actividad_6 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.rotor_actividad_6, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguienteAct6 = view.findViewById(R.id.btn_siguienteAct6);
        Button btn_atrasAct6 = view.findViewById(R.id.btn_atrasAct6);

        btn_siguienteAct6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.rotor_actividad_7);
            }
        });
        btn_atrasAct6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.rotor_actividad_5);
            }
        });


    }
}