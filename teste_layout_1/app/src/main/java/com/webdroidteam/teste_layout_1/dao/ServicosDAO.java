package com.webdroidteam.teste_layout_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.webdroidteam.teste_layout_1.models.Servicos;
import com.webdroidteam.teste_layout_1.models.Usuarios;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 17/04/2016.
 */
public class ServicosDAO {
    private DataBase dataBaseHelper;
    private SQLiteDatabase sqlDatabase;

    public ServicosDAO(Context context){
        dataBaseHelper = new DataBase(context);

    }

    private SQLiteDatabase getDatabase(){
        if (sqlDatabase == null){
            sqlDatabase = dataBaseHelper.getWritableDatabase();
        }

        return sqlDatabase;
    }

    public long salvarServico(Servicos servicos){
        ContentValues valores = new ContentValues();
        valores.put(DataBase.Servicos.ID_WEB, servicos.getId_web());
        valores.put(DataBase.Servicos.NOME, servicos.getNome());
        valores.put(DataBase.Servicos.SERVICO, servicos.getServ());
        valores.put(DataBase.Servicos.TECNICO, servicos.getColab());
        valores.put(DataBase.Servicos.ID_TECNICO, servicos.getId_colab());
        valores.put(DataBase.Servicos.OBS, servicos.getObs());
        valores.put(DataBase.Servicos.ORCAR, servicos.getOrc());
        valores.put(DataBase.Servicos.FOTO, servicos.getFot());
        valores.put(DataBase.Servicos.DATA, servicos.getData());

        if (servicos.get_id() != null){
            return getDatabase().update(DataBase.Servicos.TABELA, valores, "_id = ?", new String[]{(servicos.get_id().toString())});
        }

        return getDatabase().insert(DataBase.Servicos.TABELA, null, valores);
    }

    public boolean limparBanco(){
        return getDatabase().delete(DataBase.Servicos.TABELA, null, null) > 0;
    }

    public List<String> listarOrcar(){
        //Lista Ordens de Serviços para orçar
        List<String> osOrcar = new ArrayList<>();
        //Cursor cursor = getDatabase().query(DataBase.Servicos.TABELA, null, "orcar = ?", new String[]{"1"},null,null,null);
        Cursor cursor = getDatabase().query(DataBase.Servicos.TABELA, null, "orcar = 1", null,null,null,null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            osOrcar.add(cursor.getString(cursor.getColumnIndex("id_web")).trim());
        }
        cursor.close();
        return osOrcar;
    }

}
