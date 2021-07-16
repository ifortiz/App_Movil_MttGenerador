package n.com.mantenimientoelectrico.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.network.APIClient;
import n.com.mantenimientoelectrico.network.RegistroActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class loginApp extends Fragment {

    EditText edit_email,edit_password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_app, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_login= view.findViewById(R.id.btn_login);
         edit_email=view.findViewById(R.id.editText2);
         edit_password=view.findViewById(R.id.editText);
        ConstraintLayout btn_register=view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), RegistroActivity.class);
            startActivity(intent);
        });
        btn_login.setOnClickListener(v -> {
            Usuario newUser=new Usuario();
            newUser.setCorreo(edit_email.getText().toString());
            newUser.setContraseña(edit_password.getText().toString());

            //test remove when ready to production, change to method verifyUser

            verifyUser(newUser);


        });
       // setUserTest();
    }

  /* private  void setUserTest(){
        edit_email.setText("genaroviteluriasericom@gmail.com");
        edit_password.setText("123456789");
    }*/
    private void test(){


        SharedPreferences preferences = requireActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        preferences.edit().putLong(getString(R.string.preferences_iduser),0).apply();
        Navigation.findNavController(requireView()).navigate(R.id.mantenimiento_electrico);

    }
    private void verifyUser(Usuario user) {
        APIClient.getClient().createServiceApi().loginWithEmail(user).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
            if(response.body()!=null){
                String res =response.body().getNombre();

                Toast.makeText(requireContext(),res,Toast.LENGTH_LONG).show();
                if(res.equals(getString(R.string.response_email_notexits)) ||
                        res.equals(getString(R.string.response_not_match))){
                  Toast.makeText(requireContext(),"Lo sentimos, No pudimos encontrar los datos solicitados",Toast.LENGTH_LONG).show();
                }else{
                  Toast.makeText(requireContext(),"Bienvenido: "+res,Toast.LENGTH_LONG).show();
                    Navigation.findNavController(requireView()).navigate(R.id.mantenimiento_electrico);
                    SharedPreferences preferences = requireActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                    preferences.edit().putLong(getString(R.string.preferences_iduser),response.body().getId()).apply();
                }



            }else{
              System.out.println("data empty");
            }
        }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}