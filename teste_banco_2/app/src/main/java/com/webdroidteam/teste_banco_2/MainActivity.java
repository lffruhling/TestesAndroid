package com.webdroidteam.teste_banco_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.webdroidteam.teste_banco_2.util.Mensagem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.action_lista_usuarios:
                startActivity(new Intent(this, ListUsuariosActivity.class));
                break;
            case  R.id.action_lista_sair:
                Mensagem.msgConfirma(this, "Sair", "Deseja realemente sair?", R.drawable.ic_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                break;
            case  R.id.action_lista_logout:
                SharedPreferences preferences   = getSharedPreferences("LoginActivtyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void teste(View view){
        startActivity(new Intent(this, TesteActivity.class));
    }
}
