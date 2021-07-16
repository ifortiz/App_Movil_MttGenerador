package n.com.mantenimientoelectrico.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.adapter.ViewPagerAdapter;

public class SugerenciasFragment extends Fragment {
    private List<String> titleList= List.of("OPCION 1 ", "OPCION 2:", "OPCION 3:","OPCION 4:","OPCION 5:");
    private List<String>  descriptionList= List.of("Verificar equipo de prueba"
            , "Limpiar profundamente",
            "inspeccionar aisladores","Seccionar anillos y rotor","Cambiar anillos rozantes" );

    public SugerenciasFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sugerencias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 viewPager =view.findViewById(R.id.viewpager);
        CircleIndicator3 indicatior=view.findViewById(R.id.indicator);
        ViewPagerAdapter adapter=new ViewPagerAdapter(titleList, descriptionList);
        viewPager.setPageTransformer( new ZoomOutPageTransformer());
        viewPager.setAdapter(adapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        indicatior.setViewPager(viewPager);

    }
}