package n.com.mantenimientoelectrico.view;

import android.content.Intent;
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


public class recinto_de_escobillas_menu extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recinto_de_escobillas_menu, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_RecintoEscobillasEmpezarMtt = view.findViewById(R.id.btn_RecintoEscobillasEmpezarMtt);
        Button btn_RecintoEscobillasMenuAtras = view.findViewById(R.id.btn_RecintoEscobillasMenuAtras);
        Button btn_procedimiento=view.findViewById(R.id.btn_RecintoEscobillasProcedimiento);


        btn_procedimiento.setOnClickListener(v->{
            Intent intent = new Intent(requireContext(), PdfViewerActivity.class);
            intent.putExtra("pdfname","recintoescobilla.pdf");
            startActivity(intent);
        });
        btn_RecintoEscobillasEmpezarMtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_aviso);
            }
        });
        btn_RecintoEscobillasMenuAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_8000h);
            }
        });


    }
}