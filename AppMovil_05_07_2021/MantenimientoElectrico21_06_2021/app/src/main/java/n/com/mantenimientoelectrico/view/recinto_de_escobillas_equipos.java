package n.com.mantenimientoelectrico.view;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import n.com.mantenimientoelectrico.R;


public class recinto_de_escobillas_equipos extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recinto_de_escobillas_equipos, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_anillos_rozantes = view.findViewById(R.id.btn_anillos_rozantes);
        Button btn_FinalizarAnillosRozantes = view.findViewById(R.id.btn_FinalizarAnillosRozantes);
        Button btn_atrasEquipos = view.findViewById(R.id.btn_atrasEquipos);


        btn_FinalizarAnillosRozantes.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas));

        btn_anillos_rozantes.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.anillos_rozantes_ingresar_datos));
        btn_atrasEquipos.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_reglas_de_oro));

        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.eleccion_de_sistemas);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }


}