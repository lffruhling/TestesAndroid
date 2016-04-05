package com.webdroidteam.teste_conexao_1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WifiManager adminWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adminWifi = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);

    }

    public void testaCon (View view){
        if(isOnline(MainActivity.this)){
            Toast.makeText(MainActivity.this,"Conexão OK", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"Sem Conexão", Toast.LENGTH_LONG).show();
        }
    }

    public void ligaWifi(View view){
        setEstadoWifi();
    }

    public void setEstadoWifi(){
        if (adminWifi.isWifiEnabled()){
            //Toast.makeText(MainActivity.this, "WIFI LIGADA", Toast.LENGTH_LONG).show();
            adminWifi.setWifiEnabled(false);
            //Toast.makeText(MainActivity.this, "WIFI foi desligada", Toast.LENGTH_LONG).show();
        }else{
            //Toast.makeText(MainActivity.this, "WIFI DESLIGADA", Toast.LENGTH_LONG).show();
            adminWifi.setWifiEnabled(true);
            //Toast.makeText(MainActivity.this, "WIFI foi ligada", Toast.LENGTH_LONG).show();
        }



    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }
}
