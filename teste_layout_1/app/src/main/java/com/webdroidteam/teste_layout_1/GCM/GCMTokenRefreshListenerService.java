package com.webdroidteam.teste_layout_1.GCM;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Leonardo on 12/05/2016.
 *
 * GCMTokenRefreshListenerService.java este será utilizado para registrar o dispositivo novamente se o token servidor foi alterado.
 */
public class GCMTokenRefreshListenerService extends InstanceIDListenerService{

    //se o token for alterado, registra o dispositivo novamente

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, GCMRegistrationIntentService.class);
        startService(intent);
    }
}
