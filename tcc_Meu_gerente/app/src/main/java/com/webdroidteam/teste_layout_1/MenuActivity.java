package com.webdroidteam.teste_layout_1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Leonardo on 30/03/2016.
 */
public class MenuActivity extends FragmentActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
}
