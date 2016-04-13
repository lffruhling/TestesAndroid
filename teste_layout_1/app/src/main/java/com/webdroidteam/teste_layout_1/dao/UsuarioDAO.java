package com.webdroidteam.teste_layout_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.webdroidteam.teste_layout_1.models.Usuarios;

/**
 * Created by Leonardo on 06/04/2016.
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

    private Usuarios criaUsuario (Cursor cursor){
        Usuarios model = new Usuarios(
                cursor.getInt(cursor.getColumnIndex(DataBase.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.ID_WEB)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.EMAIL)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.USUARIO)),
                cursor.getString(cursor.getColumnIndex(DataBase.Usuarios.SENHA))
        );

        return model;
    }

    public long salvarUsuario(Usuarios usuario){
        ContentValues valores = new ContentValues();
        valores.put(DataBase.Usuarios.ID_WEB, usuario.getId_web());
        valores.put(DataBase.Usuarios.NOME, usuario.getNome());
        valores.put(DataBase.Usuarios.EMAIL, usuario.getEmail());
        valores.put(DataBase.Usuarios.USUARIO, usuario.getUsuario());
        valores.put(DataBase.Usuarios.SENHA, usuario.getSenha());

        if (usuario.get_id() != null){
            return getDatabase().update(DataBase.Usuarios.TABELA, valores, "_id = ?", new String[]{usuario.get_id().toString()});
        }

        return getDatabase().insert(DataBase.Usuarios.TABELA, null, valores);
    }

    public boolean limparBanco(){
        return getDatabase().delete(DataBase.Usuarios.TABELA, null, null) > 0;
    }

    public  boolean logar(String usuario, String email, String senha){
        Cursor cursor = getDatabase().query(DataBase.Usuarios.TABELA, null, "(USUARIO = ? or EMAIL = ?) and SENHA = ?", new String[]{usuario, email, senha},null,null,null);

        if(cursor.moveToFirst()){
            return true;
        }else
            return false;
    }

    public  void fechar(){
        dataBaseHelper.close();
        sqlDatabase = null;
    }
}
