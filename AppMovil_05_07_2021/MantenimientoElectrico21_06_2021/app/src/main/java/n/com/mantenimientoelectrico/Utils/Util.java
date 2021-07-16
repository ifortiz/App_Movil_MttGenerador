package n.com.mantenimientoelectrico.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.List;

public class Util {
    public static void showAlertDialogWithOnlyOk(String title, String message, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, id) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static void setupListenerRadiobuttons(RadioGroup radioGroup, int id_no_r, EditText editText){
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==id_no_r)
                editText.setVisibility(View.VISIBLE);
            else{
                editText.setVisibility(View.GONE);
                editText.setText("");
            }
        });
    }
    public  static  void setErrors(List<String> erros,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Errores");
        builder.setItems(erros.toArray(new String[0]), (dialogInterface, i) -> {

        });
        builder.setPositiveButton("OK", (dialog, id) -> {
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static void setupListenerRadiobuttonsList(List<RadioGroup> radioGroup, List<Integer> id_no_r, List<EditText> editText){
        for ( int i=0; i< radioGroup.size(); i++){
            int finalI = i;
            radioGroup.get(i).setOnCheckedChangeListener((group, checkedId) -> {
                if(checkedId==id_no_r.get(finalI))
                    editText.get(finalI).setVisibility(View.VISIBLE);
                else{
                    editText.get(finalI).setVisibility(View.GONE);
                    editText.get(finalI).setText("");
                }
            });
        }

    }

}
