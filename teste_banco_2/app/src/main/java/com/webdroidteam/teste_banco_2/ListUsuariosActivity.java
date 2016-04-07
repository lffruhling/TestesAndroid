package com.webdroidteam.teste_banco_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.webdroidteam.teste_banco_2.adapter.UsuarioAdapter;
import com.webdroidteam.teste_banco_2.dao.UsuarioDAO;
import com.webdroidteam.teste_banco_2.model.Usuario;
import com.webdroidteam.teste_banco_2.util.Mensagem;

import java.util.List;

public class ListUsuariosActivity
            extends AppCompatActivity
            implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView lista;
    private List<Usuario> usuarioList;
    private AlertDialog alertDialog, alertConfirm;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;

    private int idPosicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);

        alertDialog  = Mensagem.criarAlertDialog(this);
        alertConfirm = Mensagem.criarDialogConfirma(this);

        usuarioDAO      = new UsuarioDAO(this);
        usuarioList     = usuarioDAO.listarUsuarios(); // Carrega todos os usuários do banco de dados
        usuarioAdapter  = new UsuarioAdapter(this, usuarioList); //Cria lista de usuparios

        lista = (ListView) findViewById(R.id.lv_Usuarios);
        lista.setAdapter(usuarioAdapter);

        lista.setOnItemClickListener(this);
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

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = usuarioList.get(idPosicao).get_id();

        switch (which){
            //Editar
            case 0:
                Intent intent = new Intent(this, CadUsuario.class);
                intent.putExtra("USUARIO_ID",id);
                startActivity(intent);
                break;
            //Excluir
            case 1:
                alertConfirm.show();;
                break;
            case DialogInterface.BUTTON_POSITIVE:
                usuarioList.remove(idPosicao);
                usuarioDAO.removerUsuario(id);
                lista.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertConfirm.dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idPosicao = position;
        alertDialog.show();
    }
}
