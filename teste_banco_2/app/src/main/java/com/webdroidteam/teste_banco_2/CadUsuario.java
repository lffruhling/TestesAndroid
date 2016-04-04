package com.webdroidteam.teste_banco_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.webdroidteam.teste_banco_2.dao.UsuarioDAO;
import com.webdroidteam.teste_banco_2.model.Usuario;
import com.webdroidteam.teste_banco_2.util.Mensagem;

public class CadUsuario extends AppCompatActivity {
    private EditText cpoNome, cpoLogin, cpoSenha, cpoConfSenha;
    private int idUsuario;

    private UsuarioDAO usuarioDAO;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        usuarioDAO = new UsuarioDAO(this);

        cpoNome  = (EditText) findViewById(R.id.cpo_CadNome);
        cpoLogin = (EditText) findViewById(R.id.cpo_CadLogin);
        cpoSenha = (EditText) findViewById(R.id.cpo_CadSenha);
        cpoConfSenha = (EditText) findViewById(R.id.cpo_ConfSenha);
    }



    private void cadastrar(){
        boolean validacao = true;

        String nome  = cpoNome.getText().toString();
        String login = cpoLogin.getText().toString();
        String senha = cpoSenha.getText().toString();
        String confSenha = cpoConfSenha.getText().toString();
        String created_at = "nada";

        if(nome == null || nome.equals("")){
            validacao = false;
            cpoNome.setError(getString(R.string.cpoVazio));
        }
        if(login == null || login.equals("")){
            validacao = false;
            cpoLogin.setError(getString(R.string.cpoVazio));
        }
        if(senha == null || senha.equals("")){
            validacao = false;
            cpoSenha.setError(getString(R.string.cpoVazio));
        }

        if (!senha.toString().equals(confSenha.toString())){
            validacao = false;
            cpoSenha.setError(getString(R.string.confSenha));
            cpoConfSenha.setError(getString(R.string.confSenha));
        }

        if (validacao){
            usuario = new Usuario();

            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setCreated_ad(created_at);

            //Se for atualizar
            if(idUsuario > 0){
                usuario.set_id(idUsuario);
            }

            long resultado = usuarioDAO.salvarUsuario(usuario);

            if (resultado != -1){
                if(idUsuario > 0){
                    Mensagem.alertLongo(this, getString(R.string.update_success));
                }else{
                    Mensagem.alertLongo(this, getString(R.string.save_success));
                }

                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else {
                Mensagem.alertLongo(this, getString(R.string.save_error));
            }
        }
    }
    @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.cadastro, menu);

        if(idUsuario > 0){
            menu.findItem(R.id.action_menu_excluir).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_salvar:
                this.cadastrar();
                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
