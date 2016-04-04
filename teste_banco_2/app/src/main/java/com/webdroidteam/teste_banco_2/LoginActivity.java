package com.webdroidteam.teste_banco_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.webdroidteam.teste_banco_2.dao.UsuarioDAO;
import com.webdroidteam.teste_banco_2.util.Mensagem;

public class LoginActivity extends AppCompatActivity {

    private EditText cpoUsuario, cpoSenha;
    private UsuarioDAO helper;
    private CheckBox ckbConectado;

    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME = "LoginActivtyPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpoUsuario    = (EditText) findViewById(R.id.cpoUsuario);
        cpoSenha      = (EditText) findViewById(R.id.cpoSenha);
        ckbConectado  = (CheckBox) findViewById(R.id.ckb_ManterConectado);

        helper = new UsuarioDAO(this);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        boolean conectado = preferences.getBoolean(MANTER_CONECTADO, false);

        if (conectado){
            abreMenu();
        }
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
            if(helper.logar(usuario,senha)){
                //Criar arquivo de preferencia, caso check marcado
                if (ckbConectado.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean(MANTER_CONECTADO, true);
                    editor.commit();
                }

                abreMenu();

            }else{
                //Mensagem Erro
                Mensagem.alertLongo(this, getString(R.string.errorLogin));
            }
        }

    }

    private void abreMenu(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
