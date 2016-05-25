package com.example.leonardo.androidgcm4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends AppCompatActivity {
/*
Server API Key
* AIzaSyB_IteiIVwscijTiMHKQhL53GVJJRBXzMU
*Sender ID help
*1013718261627
* */

    //Criação do broadcast receiver para o registro do gcm
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando nosso broadcast receiver
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            // Quando recebe a transmissão
            // Estamos enviando a transmissão da GCM RegistrationIntentService
            @Override
            public void onReceive(Context context, Intent intent) {
                //Se o broadcast recebido for de sucesso
                //Siginifica que o dispositivo foi registrado com sucesso
                if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCESS)){
                    //Busca o registro do tokem para a intent
                    String token = intent.getStringExtra("token");
                    //Exibe um Toast com o valor do token
                    Toast.makeText(getApplicationContext(), "Registration token: " + token, Toast.LENGTH_LONG).show();

                    //se a intent não obteve sucesso de registro, exibe o erro
                }else if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR)){
                    Toast.makeText(getApplicationContext(), "GCM registration error!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_LONG).show();
                }
            }

        };


        //Checa o play service se está disponível ou não
        int resulCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        //Caso a play service não esteja disponível
        if(ConnectionResult.SUCCESS != resulCode){
            //Se a play service é suportado mas não está instalado
            if(GooglePlayServicesUtil.isUserRecoverableError(resulCode)){
                //Exibe mensagem que o play service não está instalado
                Toast.makeText(getApplicationContext(), "O serviço Google Play Service não está instalado no seu dispositvo", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resulCode, getApplicationContext());

                //Se o play service não é suportado
                //Exibe a mensagem abaxio
            }else{
                Toast.makeText(getApplicationContext(), "Este dispositivo não suporta Google Play service", Toast.LENGTH_LONG).show();
            }

            //Se a Play service está disponível
        }else{
            //Inicia intet de registro do dispositivo
            Intent intent = new Intent(this, GCMRegistrationIntentService.class);
            startService(intent);
        }
    }

    //Registra o receiver no retorno para a activity

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity","onResume");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }
}
