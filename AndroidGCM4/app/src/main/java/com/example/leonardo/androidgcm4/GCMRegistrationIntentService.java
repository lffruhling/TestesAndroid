package com.example.leonardo.androidgcm4;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by Leonardo on 12/05/2016.
 */
public class GCMRegistrationIntentService extends IntentService{
    //Constantes para sucesso e erro
    public static final String REGISTRATION_SUCESS = "RegistrationSuccess";
    public static final String REGISTRATION_ERROR = "RegistrationError";

    //Classe Construtora
    public  GCMRegistrationIntentService(){
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Registra gcm para o Dispositivo
        registerGCM();
    }

    private void registerGCM(){
        //Registra a intent inicialmente como null
        Intent registrationComplete = null;

        //Inicial um token nulo
        // Obtem um token valido
        String token = null;
        try {
            //Cria a instancia
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());

            //Busca o token da instance id
                                        //getString(R.string.gcm_defaultSenderId) ou o id do projeto gerado no console do google
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            // Exibindo o token no log para que possamos copiá-lo para enviar notificações push
            // Você também pode estender o aplicativo, armazenando o token no servidor
            Log.w("GCMRegIntentService","token: " + token);

            //Registro completo e criação da intent de sucesso
            registrationComplete = new Intent(REGISTRATION_SUCESS);

            //Colocando o token para a intent
            registrationComplete.putExtra("token", token);
        } catch (IOException e) {
            //Caso ocorra um erro
            Log.w("GCMRegIntentService", "Registration error");
            registrationComplete = new Intent(REGISTRATION_ERROR);
        }

        //Enviar um broadcast de conclusão do registro
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
