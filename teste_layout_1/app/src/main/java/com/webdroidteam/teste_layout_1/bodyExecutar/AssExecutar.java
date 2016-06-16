package com.webdroidteam.teste_layout_1.bodyExecutar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.MenuActivity;
import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.SendService.SendDeviceId;
import com.webdroidteam.teste_layout_1.SendService.SendExecutadas;
import com.webdroidteam.teste_layout_1.SignActivity;
import com.webdroidteam.teste_layout_1.conectService.ConectService;
import com.webdroidteam.teste_layout_1.models.Produtos;
import com.webdroidteam.teste_layout_1.models.Servicos;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AssExecutar extends AppCompatActivity {
    public String idOs;
    public static final int ACTIVITY_1 = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_executar);

        if(getIntent().hasExtra("byteArray")) {
            //ImageView previewThumbnail = new ImageView(this);
            ImageView iv1 = (ImageView) findViewById(R.id.img_ass_exec_ass);
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            iv1.setImageBitmap(b);
        }

        //Recebe id da os para buscar os dados
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        idOs = params.getString("IdOs");

        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(this);
        idOs = usuarioPreferences.getOsUser();
    }

    public void  voltar (View view){
        finish();
    }

    public void enviar_exec(View view){
        /*Ver log retrofit*/
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConectService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        //SendDeviceId sendDeviceId = new SendDeviceId("3", "teste");
        SendExecutadas sendExecutadas= new SendExecutadas(idOs);
        ConectService service = retrofit.create(ConectService.class);
        Call<SendExecutadas> requestUsuario = service.postExecutas(sendExecutadas); //aqui passa a classe user como parametro

        requestUsuario.enqueue(new Callback<SendExecutadas>() {
            @Override
            public void onResponse(Call<SendExecutadas> call, Response<SendExecutadas> response) {
                if(!response.isSuccessful()){
                    Log.i("POST","Erro de Resposta: "+response.code());
                    String msgErro = "Falha na Resposta do Servidor!";
                    Toast.makeText(getApplicationContext(), msgErro,Toast.LENGTH_LONG).show();
                }else{
                    Log.i("POST","CADASTRADO COM SUCESSO: "+response.code());
                    SendExecutadas catalog = response.body();
                }
            }

            @Override
            public void onFailure(Call<SendExecutadas> call, Throwable t) {
                Log.e("POST", "Falha na Requisição: " + t.getMessage());
            }
        });


        Servicos servicos = new Servicos();
        servicos.AtualizaOsExec(idOs);

        startActivity(new Intent(this, FimExecutar.class));

        finish();
    }

    public void assinarExec(View view){
        Intent act2 = new Intent(this, SignActivity.class);
        act2.putExtra("calling-activity", AssExecutar.ACTIVITY_1);
        // or ActivityConstants.ACTIVITY_3 if called form Activity3
        startActivity(act2);
        /*Intent intent = new Intent(AssExecutar.this, SignActivity.class);
        String nome_img = view.getTag().toString();

        Bundle params = new Bundle();
        params.putString("NOME_IMG", nome_img);
        intent.putExtras(params);
        startActivity(intent);*/
    }
}
