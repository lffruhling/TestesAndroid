package com.webdroidteam.teste_banco_2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.webdroidteam.teste_banco_2.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 02/04/2016.
 */
public class TarefaDAO {
    private DataBase dataBaseHelper;
    private SQLiteDatabase sqlDatabase;

    public TarefaDAO(Context context){
        dataBaseHelper = new DataBase(context);
    }

    private SQLiteDatabase getDatabase(){
        if (sqlDatabase == null){
            sqlDatabase = dataBaseHelper.getWritableDatabase();
        }

        return sqlDatabase;
    }

    private Tarefa criaTarefa (Cursor cursor){
        Tarefa model = new Tarefa(
        cursor.getInt(cursor.getColumnIndex(DataBase.Tarefas._ID)),
        cursor.getString(cursor.getColumnIndex(DataBase.Tarefas.TAREFA)),
        cursor.getString(cursor.getColumnIndex(DataBase.Tarefas.DT_CRIACAO)),
        cursor.getString(cursor.getColumnIndex(DataBase.Tarefas.DT_COMPLETADO))
        );

        return model;
    }

    public List<Tarefa> listarTarefas(){
        Cursor cursor = getDatabase().query(DataBase.Tarefas.TABELA, DataBase.Tarefas.COLUNAS, null, null, null, null, null);

        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        while (cursor.moveToNext()){
            Tarefa model = criaTarefa(cursor);
            tarefas.add(model);
        }
        cursor.close();
        return tarefas;
    }

    public long salvarTarefas(Tarefa model){
        ContentValues valores = new ContentValues();
        valores.put(DataBase.Tarefas.TAREFA, model.getTarefa());

        if (model.get_id() != null){
            return getDatabase().update(DataBase.Tarefas.TABELA, valores, "_id = ?", new String[]{model.get_id().toString()});
        }

        return getDatabase().insert(DataBase.Tarefas.TABELA, null, valores);
    }

    public boolean removerTarefa(int id){
        return getDatabase().delete(DataBase.Tarefas.TABELA, "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public  Tarefa buscarTarefasId(int id){
        Cursor cursor = getDatabase().query(DataBase.Tarefas.TABELA, DataBase.Tarefas.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Tarefa model = criaTarefa(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public  void fechar(){
        dataBaseHelper.close();
        sqlDatabase = null;
    }

}
