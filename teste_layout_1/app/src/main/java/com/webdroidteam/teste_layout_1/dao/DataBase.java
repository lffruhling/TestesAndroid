package com.webdroidteam.teste_layout_1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leonardo on 06/04/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "mg";
    private static final int VERSAO_BANCO = 1;

    public  DataBase (Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criação da tabela usuário
        db.execSQL( "Create table usuarios (_id integer primary key autoincrement, "+
                "id_web text, nome text not null, email text, usuario text, senha text not null);");

        // Cadastra usuário padrão
        db.execSQL("insert into usuarios (nome, usuario, senha) VALUES ('ADMIN', 'admin', '123');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{
        public static final String TABELA   = "usuarios";
        public static final String _ID      = "_id";
        public static final String ID_WEB   = "id_web";
        public static final String NOME     = "nome";
        public static final String EMAIL    = "email";
        public static final String USUARIO  = "usuario";
        public static final String SENHA    = "senha";

        public static final String[] COLUNAS = new String[]{
                _ID, ID_WEB, NOME, EMAIL, USUARIO, SENHA
        };
    }
}
