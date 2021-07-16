package n.com.mantenimientoelectrico.view;

import android.os.Bundle;

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

public class MenuSistemaReporteFragment extends Fragment {
    Button btn_sistemas;
    Button btn_reportes;
    Button btn_Atras;
Button btn_salir;
    public MenuSistemaReporteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_menu_sistema_reporte, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_sistemas=view.findViewById(R.id.btn_sistemas);
        btn_reportes=view.findViewById(R.id.btn_reporte);
        btn_Atras=view.findViewById(R.id.Atras);
        btn_salir = view.findViewById(R.id.btn_salir);
        btn_salir.setOnClickListener( v ->{
            requireActivity().finish();
        });
        btn_sistemas.setOnClickListener(v->     Navigation.findNavController(v).navigate(R.id.eleccion_de_sistemas));
        btn_Atras.setOnClickListener(v->     Navigation.findNavController(v).navigate(R.id.mantenimiento_electrico));
        btn_reportes.setOnClickListener(v->loadReporteFragment() );
    }


    private void loadReporteFragment() {
        /*no me carga nav graph. remplazar con navigation*/
        Fragment nuevoFragmento = new ReportesFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
        transaction.addToBackStack(null);

        // Commit a la transacción
        transaction.commit();
    }
}