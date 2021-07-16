package n.com.mantenimientoelectrico;

import androidx.appcompat.app.AppCompatActivity;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("paso 1 ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}