package com.webdroidteam.teste_banco_2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Leonardo on 02/04/2016.
 */
public class DataBase extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "teste";
    private static final int VERSAO_BANCO = 1;



    public  DataBase (Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criação da tabela usuário
        db.execSQL( "Create table usuarios (_id integer primary key autoincrement, "+
                    "nome text not null, login text not null, senha text not null);");

        //Tabela de tarefas
        db.execSQL( "Create table tarefas (_id integer primary key autoincrement, "+
                    "tarefa text not null, dt_criacao datetime default current_datetime, dt_completado datetime);");

        // Cadastra usuário padrão
       /* // FAIL
       ContentValues map = new ContentValues();
        map.put("nome","administrado");
        map.put("login","admin");
        map.put("senha","123");
        try{
            getWritableDatabase().insert(Usuarios.TABELA,null,map);
        }catch (SQLException e){
            Log.e("SQL->ERROR: ", e.toString());
        }*/

        db.execSQL("insert into usuarios (nome, login, senha) VALUES ('ADMIN', 'admin', '123');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";

        public static final String[] COLUNAS = new String[]{
            _ID, NOME, LOGIN, SENHA
        };
    }

    public static class Tarefas{
        public static final String TABELA = "tarefas";
        public static final String _ID = "_id";
        public static final String TAREFA = "tarefa";
        public static final String DT_CRIACAO = "dt_criacao";
        public static final String DT_COMPLETADO = "dt_completado";

        public static final String[] COLUNAS = new String[]{
                _ID, TAREFA, DT_CRIACAO, DT_COMPLETADO
        };
    }
}
