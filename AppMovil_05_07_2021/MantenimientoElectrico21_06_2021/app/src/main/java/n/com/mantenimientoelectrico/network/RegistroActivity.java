package n.com.mantenimientoelectrico.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button btn_save=findViewById(R.id.btn_save);
        EditText edit_nombre=findViewById(R.id.edit_nombre);
        EditText edit_apellido=findViewById(R.id.edit_apellido);
        EditText edit_email=findViewById(R.id.edit_email);
        EditText edit_password=findViewById(R.id.edit_password);

        btn_save.setOnClickListener(view->{
            Usuario newUser=new Usuario();
            newUser.setNombre(edit_nombre.getText().toString());
            newUser.setApellido(edit_apellido.getText().toString());
            newUser.setCorreo(edit_email.getText().toString());
            newUser.setContraseña(edit_password.getText().toString());

                saveUser(newUser);
        });
    }

    private void saveUser(Usuario user){
        APIClient.getClient().createServiceApi().saveUser(user).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.body()!=null){
                    String res =response.body().getNombre();

                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    if(res.equals(getString(R.string.response_email_notexits)) ||
                            res.equals(getString(R.string.response_not_match))){
                        Toast.makeText(getApplicationContext(),"Datos incorrectos",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario guardado"+res,Toast.LENGTH_LONG).show();


                    }

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("failure-mainact",t.getMessage());
            }
        });
    }
}