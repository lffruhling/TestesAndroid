package com.webdroidteam.teste_layout_1.bodyExecutar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webdroidteam.teste_layout_1.R;

public class AssExecutar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_executar);
    }

    public void  voltar (View view){
        finish();
    }

    public void enviar_exec(View view){
        startActivity(new Intent(this, FimExecutar.class));
        finish();
    }
}
