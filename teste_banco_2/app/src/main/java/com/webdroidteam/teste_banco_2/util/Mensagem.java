package com.webdroidteam.teste_banco_2.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

    public  static void addMsgOk(Activity activity, String title, String msg, int icon){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setNeutralButton("OK", null);
        alert.setIcon(icon);
        alert.show();
    }

    public static void msgConfirma(Activity activity, String title, String msg, int icon, DialogInterface.OnClickListener listener){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setPositiveButton("Sim", listener);
        alert.setNegativeButton("Não", listener);
        alert.setIcon(icon);
        alert.show();
    }

    public static AlertDialog criarAlertDialog(Activity activity){
        final  CharSequence[] items = {
                "Editar",
                "Excluir"
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Opções");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);

        return alert.create();
    }

    public static AlertDialog criarDialogConfirma(Activity activity){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage("Deseja excluir?");
        alert.setPositiveButton("Sim", (DialogInterface.OnClickListener) activity);
        alert.setNegativeButton("Não", (DialogInterface.OnClickListener) activity);

        return alert.create();
    }
}
