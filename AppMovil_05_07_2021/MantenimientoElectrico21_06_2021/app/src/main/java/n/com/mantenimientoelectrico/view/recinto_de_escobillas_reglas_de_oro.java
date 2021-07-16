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
import android.widget.EditText;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.Utils.Util;
import n.com.mantenimientoelectrico.Utils.Validator;

public class recinto_de_escobillas_reglas_de_oro extends Fragment {

    RadioGroup rg_r1,rg_r2,rg_r3,rg_r4,rg_r5;
    EditText et_r1_nocumple,et_r2_nocumple,et_r3_nocumple,et_r4_nocumple,et_r5_nocumple;
    private List <RadioGroup>listRg = new ArrayList<>();
    private List<EditText> listEditext = new ArrayList<>();
    private List<Integer> listIdNo= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recinto_de_escobillas_reglas_de_oro, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_siguienteReglasOro = view.findViewById(R.id.btn_siguienteReglasOro);
        Button btn_atrasReglasOro = view.findViewById(R.id.btn_atrasReglasOro);

        rg_r1=view.findViewById(R.id.radiogroup_regla_1);
        rg_r2=view.findViewById(R.id.radiogroup_regla_2);
        rg_r3=view.findViewById(R.id.radiogroup_regla_3);
        rg_r4=view.findViewById(R.id.radiogroup_regla_4);
        rg_r5=view.findViewById(R.id.radiogroup_regla_5);
        listRg.add(rg_r1);
        listRg.add(rg_r2);
        listRg.add(rg_r3);
        listRg.add(rg_r4);
        listRg.add(rg_r5);

        et_r1_nocumple=view.findViewById(R.id.editext_no_cumpleRegla1);
        et_r2_nocumple=view.findViewById(R.id.editext_no_cumpleRegla2);
        et_r3_nocumple=view.findViewById(R.id.editext_no_cumpleRegla3);
        et_r4_nocumple=view.findViewById(R.id.editext_no_cumpleRegla4);
        et_r5_nocumple=view.findViewById(R.id.editext_no_cumpleRegla5);
        listEditext.add(et_r1_nocumple);
        listEditext.add(et_r2_nocumple);
        listEditext.add(et_r3_nocumple);
        listEditext.add(et_r4_nocumple);
        listEditext.add(et_r5_nocumple);

        listIdNo.add(R.id.checkBox_no_regla_1);
        listIdNo.add(R.id.checkBox_no_regla_2);
        listIdNo.add(R.id.checkBox_no_regla_3);
        listIdNo.add(R.id.checkBox_no_regla_4);
        listIdNo.add(R.id.checkBox_no_regla_5);

        Util.setupListenerRadiobuttonsList(listRg,listIdNo,listEditext);
        btn_siguienteReglasOro.setOnClickListener(v -> validar());
        btn_atrasReglasOro.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.recinto_de_escobillas_menu));
    }

    private  void validar(){
        List<String> errors = Validator.validarRadiobuttonsErrors(listRg,listIdNo,listEditext);
        if(errors.size()>=1){
            Util.setErrors(errors,requireContext());
            errors.clear();
        }else{
            Navigation.findNavController(requireView()).navigate(R.id.recinto_de_escobillas_equipos);
        }
    }
}