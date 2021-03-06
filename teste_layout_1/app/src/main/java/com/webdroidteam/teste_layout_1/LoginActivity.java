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
import com.webdroidteam.teste_layout_1.dao.UsuarioDAO;
import com.webdroidteam.teste_layout_1.models.ServiceCatalog;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;
import com.webdroidteam.teste_layout_1.util.Mensagem;
import android.support.v7.widget.AppCompatCheckBox;

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
    private UsuarioDAO helper;
    //private AppCompatCheckBox ckbConectado;
    //final CheckBox ckbConectado  = (CheckBox) findViewById(R.id.ckb_ManterConectado);

    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME = "LoginActivtyPreferences";

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpoUsuario    = (EditText) findViewById(R.id.cpoUsuario);
        cpoSenha      = (EditText) findViewById(R.id.cpoSenha);
        //final CheckBox ckbConectado  = (CheckBox) findViewById(R.id.ckb_ManterConectado);

        helper = new UsuarioDAO(this);

        /*Para usar isto, primeiro ver como retornoar o id do usuário logado*/
        //SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        //boolean conectado = preferences.getBoolean(MANTER_CONECTADO, false);

        //if (conectado){
        //    abreMenu();
        //}
    }

    public void logar(View view){
        String usuario = cpoUsuario.getText().toString();
        String senha = cpoSenha.getText().toString();

        boolean validacao = true;

        if (usuario == null || usuario.equals("")){
            validacao = false;
            cpoUsuario.setError(getString(R.string.usuarioVazio));
        }

        if (senha == null || senha.equals("")){
            validacao = false;
            cpoSenha.setError(getString(R.string.senhaVazio));
        }

        if (validacao){
            // Logar
            if(helper.logar(usuario,usuario,senha)){
                //Descomentar para criar arquivo de permanencia de login
                //SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreferences.edit();

                //editor.putBoolean(MANTER_CONECTADO, true);
                //editor.commit();
                abreMenu();

            }else{
                //Mensagem Erro
                Mensagem.alertLongo(this, getString(R.string.errorLogin));
            }
        }

    }

    private void abreMenu(){
        String usuario = cpoUsuario.getText().toString();
        String senha = cpoSenha.getText().toString();



        if ((usuario != null || !usuario.trim().equals("")) && (senha != null || !senha.trim().equals(""))) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            String id_tec = helper.IdTec(usuario, usuario, senha);
            //Envia para a tela de OS, qual o usuário logado
            Bundle params = new Bundle();
            params.putString("ID_TEC", id_tec);
            intent.putExtras(params);

            UsuarioPreferences usuarioPreferences = new UsuarioPreferences(this);
            usuarioPreferences.setIdUser(id_tec);

            SendDeviceId sendDeviceId = new SendDeviceId(usuarioPreferences.getIdUser(), usuarioPreferences.getToken());

            Call<RCadId> ResponseCadId = ApiFactory.conectService().postIdDevice(sendDeviceId);

        ResponseCadId.enqueue(new Callback<RCadId>() {
            @Override
            public void onResponse(Call<RCadId> call, Response<RCadId> response) {
                if(!response.isSuccessful()){
                    Log.i("POST","ErroRRR de insucesso: "+response.code());
                }else{
                    Log.i("POST","CADASTRADO COM SUCESSO: "+response.code());
                    RCadId catalog = response.body();
                    Log.i("POST","Resposta "+catalog.getStatus());
                }
            }

            @Override
            public void onFailure(Call<RCadId> call, Throwable t) {

            }
        });

            startActivity(intent);
            setContentView(R.layout.activity_menu);
            finish();
        }
    }

    /*public void verificaLogin (View view){
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_menu);

    }*/
}
