package com.webdroidteam.teste_banco_2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.webdroidteam.teste_banco_2.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 02/04/2016.
 */
public class UsuarioDAO {
    private DataBase dataBaseHelper;
    private SQLiteDatabase sqlDatabase;

    public UsuarioDAO(Context context){
        dataBaseHelper = new DataBase(context);

    }

    private SQLiteDatabase getDatabase(){
        if (sqlDatabase == null){
            sqlDatabase = dataBaseHelper.getWritableDatabase();
        }

        return sqlDatabase;
    }

    private Usuario criaUsuario (Cursor cursor){
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DataBase.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.SENHA))
        );

        return model;
    }

    public List<Usuario> listarUsuarios(){
        Cursor cursor = getDatabase().query(DataBase.Usuarios.TABELA, DataBase.Usuarios.COLUNAS, null, null, null, null, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while (cursor.moveToNext()){
            Usuario model = criaUsuario(cursor);
            usuarios.add(model);
        }
        cursor.close();
        return usuarios;
    }

    public long salvarUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(DataBase.Usuarios.NOME, usuario.getNome());
        valores.put(DataBase.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DataBase.Usuarios.SENHA, usuario.getSenha());

        if (usuario.get_id() != null){
            return getDatabase().update(DataBase.Usuarios.TABELA, valores, "_id = ?", new String[]{usuario.get_id().toString()});
        }

        return getDatabase().insert(DataBase.Usuarios.TABELA, null, valores);
    }

    public boolean removerUsuario(int id){
        return getDatabase().delete(DataBase.Usuarios.TABELA, "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public  Usuario buscarUsuarioId(int id){
        Cursor cursor = getDatabase().query(DataBase.Usuarios.TABELA, DataBase.Usuarios.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Usuario model = criaUsuario(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public  boolean logar(String usuario, String senha){
        Cursor cursor = getDatabase().query(DataBase.Usuarios.TABELA, null, "LOGIN = ? and SENHA = ?", new String[]{usuario, senha},null,null,null);

        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public  void fechar(){
        dataBaseHelper.close();
        sqlDatabase = null;
    }

}
