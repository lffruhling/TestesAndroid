package com.webdroidteam.teste_layout_1.bodyExecutar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.bodyOrcar.FimOrcamento;

public class ExecutarOs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executar_os);
    }

    public void  voltar (View view){
        finish();
    }

    public void iniciar_os(View view){
        startActivity(new Intent(this, ProdutosOs.class));
        finish();
    }
}
