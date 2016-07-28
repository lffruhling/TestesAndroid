package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.webdroidteam.teste_layout_1.MenuActivity;
import com.webdroidteam.teste_layout_1.R;

import java.util.Timer;
import java.util.TimerTask;

public class FimOrcamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_orcamento);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();

                Intent intent = new Intent(FimOrcamento.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
