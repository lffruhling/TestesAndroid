package com.webdroidteam.teste_banco_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.webdroidteam.teste_banco_2.adapter.UsuarioAdapter;
import com.webdroidteam.teste_banco_2.dao.UsuarioDAO;
import com.webdroidteam.teste_banco_2.model.Usuario;

import java.util.List;

public class ListUsuariosActivity extends AppCompatActivity {

    private ListView lista;
    private List<Usuario> usuarioList;

    private UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);

        usuarioDAO = new UsuarioDAO(this);
        usuarioList = usuarioDAO.listarUsuarios(); // Carrega todos os usuários do banco de dados
        usuarioAdapter = new UsuarioAdapter(this, usuarioList); //Cria lista de usuparios

        lista = (ListView) findViewById(R.id.lv_Usuarios);
        lista.setAdapter(usuarioAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_list_usuarios, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_cadastra_usuarios){
            startActivity(new Intent(this, CadUsuario.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
