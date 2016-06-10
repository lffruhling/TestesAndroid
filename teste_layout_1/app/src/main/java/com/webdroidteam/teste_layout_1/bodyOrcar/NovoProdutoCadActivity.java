package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.adapter.AdapterNovoProdCad;
import com.webdroidteam.teste_layout_1.models.NovoProdutoOrcado;

public class NovoProdutoCadActivity extends AppCompatActivity {

    private String idOs;
    private RecyclerView rvProdutos;
    private AdapterNovoProdCad adapter;
    private RecyclerView.LayoutManager lmRecycler;

    private NovoProdutoOrcado novoProdutoOrcado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto_cad);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        idOs = params.getString("IdOs");

        novoProdutoOrcado.detalhesNovoProdcad(idOs);

        rvProdutos = (RecyclerView) findViewById(R.id.lv_prod_cad);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvProdutos.setHasFixedSize(true);

        // use a linear layout manager
        lmRecycler = new LinearLayoutManager(this);
        rvProdutos.setLayoutManager(lmRecycler);

        // specify an adapter (see also next example)
        adapter = new AdapterNovoProdCad(this,NovoProdutoOrcado.detalhesNovoProdcad(idOs));
        rvProdutos.setAdapter(adapter);
    }

    public void  voltar (View view){
        finish();
    }

    public void finalizar(View view){
        Intent intent = null;
        intent = new Intent(this, AssOrcamento.class);
        //Envia a OS Clicada no RecyclerView
        Bundle params = new Bundle();
        params.putString("IdOs", idOs);
        intent.putExtras(params);
        startActivity(intent);

        finish();
    }
}
