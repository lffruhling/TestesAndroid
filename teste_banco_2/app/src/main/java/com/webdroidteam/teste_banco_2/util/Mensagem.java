package com.webdroidteam.teste_banco_2.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Leonardo on 02/04/2016.
 */
public class Mensagem {
    public static void alertLongo(Activity activity, String alert){
        Toast.makeText(activity, alert, Toast.LENGTH_SHORT).show();
    }

    public static void alertCurto(Activity activity, String alert){
        Toast.makeText(activity, alert, Toast.LENGTH_SHORT).show();
    }
}
