package com.webdroidteam.teste_layout_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.conectService.ConectService;
import com.webdroidteam.teste_layout_1.models.ServiceCatalog;
import com.webdroidteam.teste_layout_1.models.Usuarios;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends Activity {
    WifiManager adminWifi;
    private static final String TAG = "LOG-LEO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Teste de Conexão
        adminWifi = (WifiManager) SplashActivity.this.getSystemService(Context.WIFI_SERVICE);

        if (testa3G(this) || isConected(this)){
            Toast.makeText(SplashActivity.this,"Entrou", Toast.LENGTH_LONG).show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ConectService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ConectService service = retrofit.create(ConectService.class);
            Call<ServiceCatalog> requestCatalog = service.listCatalog();

            requestCatalog.enqueue(new Callback<ServiceCatalog>() {
                @Override
                public void onResponse(Call<ServiceCatalog> call, Response<ServiceCatalog> response) {
                    if(!response.isSuccessful()){
                        Log.i(TAG,"ErroRRR de insucesso: "+response.code());
                    }else{
                        //Requisição com sucesso
                        ServiceCatalog catalog = response.body();
                        Integer i = 0;
                        for(Usuarios c : catalog.usuarios){
                            Log.i("OKKKKKK",String.format("%s: %s",c.id,c.nome));
                            i++;
                            Log.i("Array: ", i.toString());
                            Log.i(TAG,"---------------------");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ServiceCatalog> call, Throwable t) {
                    Log.e(TAG,"--------------Erro: "+t.getMessage());
                }
            });

            /*new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();

                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }, 3000);*/
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
}
