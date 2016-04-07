package com.webdroidteam.teste_layout_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.webdroidteam.teste_layout_1.dao.UsuarioDAO;

/**
 * Created by Leonardo on 30/03/2016.
 */
public class LoginActivity extends Activity {
    private UsuarioDAO helper;
    private CheckBox ckbConectado;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void verificaLogin (View view){
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_menu);

    }
}
