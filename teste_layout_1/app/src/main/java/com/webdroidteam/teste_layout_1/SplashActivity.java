package com.webdroidteam.teste_layout_1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.data.DataBufferObserver;
import com.webdroidteam.teste_layout_1.GCM.GCMRegistrationIntentService;
import com.webdroidteam.teste_layout_1.conectService.ApiFactory;
import com.webdroidteam.teste_layout_1.conectService.ConectService;
import com.webdroidteam.teste_layout_1.models.ServiceCatalog;
import com.webdroidteam.teste_layout_1.models.Usuarios;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SplashActivity extends Activity {
    /*
    * Server API Key help
    * AIzaSyAlx7QtwtutZG0Kx0klesXzhB2Q2310k08
    * Sender ID help
    * 711874135734
    *
    * GCM Inicio
    * */

    //Criação do broadcast receiver para o registro do gcm
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    WifiManager adminWifi;
    private static final String TAG = "WS";
    private Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //GCM
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

        //Registra o receiver no retorno para a activity


        //Teste de Conexão
        adminWifi = (WifiManager) SplashActivity.this.getSystemService(Context.WIFI_SERVICE);

        if (testa3G(this) || isConected(this)){
            ApiFactory.conectService().listCatalog()
            .subscribeOn(Schedulers.io())
                    .map(r -> r.usuarios)
                    .doOnError(error -> onError(error))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onResponse, error -> onError(error));
        }else{
            alertDialog(this);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }, 3000);
        }
    }

    private void onResponse(List<Usuarios> usuarioses) {
        Log.d(TAG,"response ");

       usuario.limpaBanco();

        for(Usuarios U : usuarioses){
            U.save();
        }

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void onError(Throwable error) {
        Log.d(TAG,"error "+error.getMessage());
    }

    public static boolean isConected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }

    public void alertDialog(final Context context) {
        try {
            AlertDialog.Builder alerta = new AlertDialog.Builder(context);
            //alerta.setIcon(R.drawable.abc_ic_go);
            alerta.setTitle("Alerta");
            alerta.setMessage("Sinal não encontrado.");
            alerta.setPositiveButton("Ativar Wifi?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    setEstadoWifi();
                }
            });
            alerta.setNegativeButton("Ativar Dados Móveis?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        testeMobile(getCurrentFocus());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
            alerta.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alerta.show();
        } catch (Exception erro) {
            Log.e("Erro: ", erro.getMessage(), erro);
        }
    }

    public void testeMobile(View view) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        //if (testa3G(this)) {
        //    Toast.makeText(this, "3G LIGADA", Toast.LENGTH_LONG).show();
       // } else {
       //     Toast.makeText(this, "3G DESLIGADA", Toast.LENGTH_LONG).show();
            setMobileDataEnabled(SplashActivity.this, true);
       // }
    }

    public void setEstadoWifi() {
        //if (adminWifi.isWifiEnabled()) {
         //   adminWifi.setWifiEnabled(false);
        //} else {
            adminWifi.setWifiEnabled(true);
        //}
    }

    public boolean testa3G(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobile.isConnected();
    }

    private void setMobileDataEnabled(Context context, boolean enabled) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, NoSuchFieldException {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
        iConnectivityManagerField.setAccessible(true);
        final Object iConnectivityManager = iConnectivityManagerField.get(conman);
        final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
    }

    //GCM
    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity","onResume");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }
}
