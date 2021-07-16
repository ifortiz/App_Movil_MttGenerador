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


public class trafo_auxiliares_menu extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.trafo_auxiliares_menu, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_TrafoAuxiEmpezarMtt = view.findViewById(R.id.btn_TrafoAuxiEmpezarMtt);
        Button btn_AtrasTrafoAuxiMenu = view.findViewById(R.id.btn_AtrasTrafoAuxiMenu);

        btn_TrafoAuxiEmpezarMtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.trafo_auxiliares_aviso);
            }
        });
        btn_AtrasTrafoAuxiMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.trafo_auxiliares_8000h);
            }
        });
    }
}