package com.webdroidteam.teste_layout_1.bodyExecutar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webdroidteam.teste_layout_1.R;

public class FotosOs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_os);
    }

    public void  voltar (View view){
        finish();
    }

    public void finalizar(View view){
        startActivity(new Intent(this, AssExecutar.class));
        finish();
    }
}
