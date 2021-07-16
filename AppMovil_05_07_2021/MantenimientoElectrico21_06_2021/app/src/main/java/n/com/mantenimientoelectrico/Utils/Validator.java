package n.com.mantenimientoelectrico.Utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    //selecciona que si, esta bien
    // selecciona que no,  y menciona una descripn, esta bien
    // si selecciona que no, pero no escribe nada, esta mal
    public static String validarRadiobuttons(RadioGroup radioGroup, int id_r_no, EditText editText, Context context) {
        String cumplimiento="";
        if(radioGroup.getCheckedRadioButtonId() == -1)
            Toast.makeText(context, "NO PUEDE DEJAR CAMPOS VACIOS", Toast.LENGTH_LONG).show(); //no seleccionon ninguno
        else {
            if( radioGroup.getCheckedRadioButtonId() != id_r_no ){
                cumplimiento = "SI CUMPLIO"; //si es diferente que respuesta no, entonces selecciono que si
            }else{
                if(editText.getText().toString().trim().equals(""))
                    Toast.makeText(context, "Describa por que no se cumple la actividad", Toast.LENGTH_LONG).show();
                else
                    cumplimiento = "NO CUMPLIO";
            }
        }
        return cumplimiento;
    }

    public static List<String> validarRadiobuttonsErrors(List<RadioGroup> radioGroups, List<Integer> id_r_nos, List<EditText> editTexts) {
        List<String> errors = new ArrayList<>();
        for ( int i =0; i<radioGroups.size(); i++){
            if(radioGroups.get(i).getCheckedRadioButtonId() == -1)
                errors.add("Regla #"+(i+1)+" No fue seleccionada, porfavor SELECCIONE UNA OPCION");
            else  if(editTexts.get(i).getText().toString().trim().equals("")
                    && radioGroups.get(i).getCheckedRadioButtonId() == id_r_nos.get(i) )
                errors.add("Regla #"+(i+1)+" Porfavor Escriba un MOTIVO por el cual no se cumple");
        }
     return  errors;
    }
}
