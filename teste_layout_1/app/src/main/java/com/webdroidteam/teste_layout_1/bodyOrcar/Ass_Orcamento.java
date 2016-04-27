package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webdroidteam.teste_layout_1.R;

public class Ass_Orcamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_orcamento);
    }

    public void  voltar (View view){
        finish();
    }

    public void enviar_orc(View view){
        startActivity(new Intent(this, FimOrcamento.class));
        finish();
    }
}
