package com.webdroidteam.teste_layout_1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.webdroidteam.teste_layout_1.SendService.RCadId;
import com.webdroidteam.teste_layout_1.SendService.SendDeviceId;
import com.webdroidteam.teste_layout_1.conectService.ApiFactory;
import com.webdroidteam.teste_layout_1.conectService.ConectService;
import com.webdroidteam.teste_layout_1.models.ServiceCatalog;
import com.webdroidteam.teste_layout_1.models.Usuarios;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;
import com.webdroidteam.teste_layout_1.util.Mensagem;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leonardo on 30/03/2016.
 */
public class LoginActivity extends Activity {
    private EditText cpoUsuario, cpoSenha;
    private static final String PREFERENCE_NAME = "LoginActivtyPreferences";

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpoUsuario    = (EditText) findViewById(R.id.cpoUsuario);
        cpoSenha      = (EditText) findViewById(R.id.cpoSenha);

        /*Para usar isto, primeiro ver como retornoar o id do usuário logado*/
        //SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        //boolean conectado = preferences.getBoolean(MANTER_CONECTADO, false);

        //if (conectado){
        //    abreMenu();
        //}
    }

    public void logar(View view){
        String sUsuario = cpoUsuario.getText().toString();
        String sSenha = cpoSenha.getText().toString();

        boolean validacao = true;

        if (sUsuario == null || sUsuario.equals("")){
            validacao = false;
            cpoUsuario.setError(getString(R.string.usuarioVazio));
        }

        if (sSenha == null || sSenha.equals("")){
            validacao = false;
            cpoSenha.setError(getString(R.string.senhaVazio));
        }

        if (validacao){
            // Logar
            Usuarios usuario = Usuarios.getResulLogin(sUsuario,sUsuario,sSenha);
            if(usuario != null){
                //Descomentar para criar arquivo de permanencia de login
                //SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreferences.edit();

                //editor.putBoolean(MANTER_CONECTADO, true);
                //editor.commit();
                abreMenu(usuario);

            }else{
                //Mensagem Erro
                Mensagem.alertLongo(this, getString(R.string.errorLogin));
            }
        }

    }

    private void abreMenu(Usuarios usuario){

        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

        String id_tec = usuario.getId_web();
        //Envia para a tela de OS, qual o usuário logado
        Bundle params = new Bundle();
        params.putString("ID_TEC", id_tec);
        intent.putExtras(params);

        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(this);
        usuarioPreferences.setIdUser(id_tec);

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
        SendDeviceId sendDeviceId = new SendDeviceId(usuarioPreferences.getIdUser(), usuarioPreferences.getToken());
        ConectService service = retrofit.create(ConectService.class);
        Call<SendDeviceId> requestUsuario = service.postIdDevice(sendDeviceId); //aqui passa a classe user como parametro

        requestUsuario.enqueue(new Callback<SendDeviceId>() {
            @Override
            public void onResponse(Call<SendDeviceId> call, Response<SendDeviceId> response) {
                if(!response.isSuccessful()){
                    Log.i("POST","Erro de Resposta: "+response.code());
                    String msgErro = "Falha na Resposta do Servidor!";
                    Toast.makeText(getApplicationContext(), msgErro,Toast.LENGTH_LONG).show();
                }else{
                    Log.i("POST","CADASTRADO COM SUCESSO: "+response.code());
                    SendDeviceId catalog = response.body();
                }
            }

            @Override
            public void onFailure(Call<SendDeviceId> call, Throwable t) {
                Log.e("POST", "Falha na Requisição: " + t.getMessage());
            }
        });
        finish();
        startActivity(intent);
        setContentView(R.layout.activity_menu);
    }

}
