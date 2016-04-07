package com.webdroidteam.teste_retrofit_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Leonardo on 28/03/2016.
 */
public class atividade1 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_atividade1);
    }

    public void  voltar (View view){
        finish();
    }
}
