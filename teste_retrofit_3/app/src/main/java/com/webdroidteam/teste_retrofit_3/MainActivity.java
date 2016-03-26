package com.webdroidteam.teste_retrofit_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.webdroidteam.teste_retrofit_3.models.Clientes;
import com.webdroidteam.teste_retrofit_3.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TESTEEEEE: --- ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(UdacityService.BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        UdacityService service = retrofit.create(UdacityService.class);
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"ErroRRR de insucesso: "+response.code());
                }else{
                    //Requisição com sucesso
                    UdacityCatalog catalog = response.body();

                    for(Clientes c : catalog.clientes){
                        Log.i("OKKKKKK",String.format("%s: %s",c.id,c.nome));
                        Log.i(TAG,"---------------------");
                    }
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e(TAG,"--------------Erro: "+t.getMessage());
            }
        });

    }
}
