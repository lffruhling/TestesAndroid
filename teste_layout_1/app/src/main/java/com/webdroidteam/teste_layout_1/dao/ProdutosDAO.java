package com.webdroidteam.teste_layout_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.webdroidteam.teste_layout_1.models.Produtos;

/**
 * Created by Leonardo on 17/04/2016.
 */
public class ProdutosDAO {
    private DataBase dataBaseHelper;
    private SQLiteDatabase sqlDatabase;

    public ProdutosDAO(Context context){
        dataBaseHelper = new DataBase(context);

    }

    private SQLiteDatabase getDatabase(){
        if (sqlDatabase == null){
            sqlDatabase = dataBaseHelper.getWritableDatabase();
        }

        return sqlDatabase;
    }

    public long salvarProdutos(Produtos produtos){
        ContentValues valores = new ContentValues();
        valores.put(DataBase.Produtos.ID_PROD, produtos.getId_pro());
        valores.put(DataBase.Produtos.ID_OS, produtos.getId_os());
        valores.put(DataBase.Produtos.NOME, produtos.getNome());
        valores.put(DataBase.Produtos.DESC, produtos.getDesc());
        valores.put(DataBase.Produtos.QUANT, produtos.getQuant());

        if (produtos.get_id() != null){
            return getDatabase().update(DataBase.Produtos.TABELA, valores, "_id = ?", new String[]{(produtos.get_id().toString())});
        }

        return getDatabase().insert(DataBase.Produtos.TABELA, null, valores);
    }

    public boolean limparBanco(){
        return getDatabase().delete(DataBase.Produtos.TABELA, null, null) > 0;
    }
}
