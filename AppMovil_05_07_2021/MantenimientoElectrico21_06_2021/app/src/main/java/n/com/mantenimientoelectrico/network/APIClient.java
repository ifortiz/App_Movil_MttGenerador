package n.com.mantenimientoelectrico.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
  // private static final String BASE_URL="http://172.16.58.84:8888/"; /*trabajo*/
   private static final String BASE_URL="http://192.168.1.2:8888/"; /*casa*/

    private static APIClient instance;
    private Retrofit retrofit;


    private APIClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public static synchronized APIClient getClient(){
        if(instance==null){
            instance=new APIClient();
        }
        return instance;
    }

    public ApiInterface createServiceApi(){
        return retrofit.create(ApiInterface.class);
    }

}

