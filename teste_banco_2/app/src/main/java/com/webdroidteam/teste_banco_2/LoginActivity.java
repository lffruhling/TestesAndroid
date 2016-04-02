package com.webdroidteam.teste_banco_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.webdroidteam.teste_banco_2.dao.UsuarioDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText cpoUsuario, cpoSenha;
    private UsuarioDAO helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpoUsuario = (EditText) findViewById(R.id.cpoUsuario);
        cpoSenha = (EditText) findViewById(R.id.cpoSenha);

        helper = new UsuarioDAO(this);
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
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }

    }
}
