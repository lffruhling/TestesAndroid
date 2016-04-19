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

        //Criação da tabela serviços
        db.execSQL( "Create table os (_id integer primary key autoincrement, "+
                    "id_web int, nome text, tpo_serv text, nome_colab text, obs text, orcar int, foto int, data text );");

        //Criação da tabela Produtos -> serviços
        db.execSQL( "Create table produtos (_id integer primary key autoincrement, "+
                    "id_prod_web int, id_os text, nome text, desc text, quant int);");

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

    public static class Servicos{
        public static final String TABELA   = "os";
        public static final String _ID      = "_id";
        public static final String ID_WEB   = "id_web";
        public static final String NOME     = "nome";
        public static final String SERVICO  = "tpo_serv";
        public static final String TECNICO  = "nome_colab";
        public static final String OBS      = "obs";
        public static final String ORCAR    = "orcar";
        public static final String FOTO     = "foto";
        public static final String DATA     = "data";

        public static final String[] COLUNAS = new String[]{
                _ID, ID_WEB, NOME, SERVICO, TECNICO, OBS, ORCAR, FOTO, DATA
        };
    }

    public static class Produtos{
        public static final String TABELA   = "produtos";
        public static final String _ID      = "_id";
        public static final String ID_PROD  = "id_prod_web";
        public static final String ID_OS    = "id_os";
        public static final String NOME     = "nome";
        public static final String DESC     = "desc";
        public static final String QUANT    = "quant";

        public static final String[] COLUNAS = new String[]{
                _ID, ID_PROD, ID_OS, NOME, DESC, QUANT
        };
    }
}
