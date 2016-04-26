package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webdroidteam.teste_layout_1.R;

public class CriarOrcamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_orcamento);
    }

    public void  voltar (View view){
        finish();
    }
}
