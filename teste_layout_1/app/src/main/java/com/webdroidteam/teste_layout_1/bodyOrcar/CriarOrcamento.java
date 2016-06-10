package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.adapter.AdapterProduto;
import com.webdroidteam.teste_layout_1.models.Produtos;
import com.webdroidteam.teste_layout_1.models.Servicos;

public class CriarOrcamento extends AppCompatActivity {
    private Servicos servicos;
    private Servicos detalhes;
    private TextView textViewIdOs, textViewCli;
    private String idOs;
    private RecyclerView rvProdutos;
    private AdapterProduto adapter;
    private RecyclerView.LayoutManager lmRecycler;
    private Produtos produtos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_orcamento);

        //Recebe id da os para buscar os dados
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        idOs = params.getString("IdOs");

        detalhes = servicos.detalhesOs(idOs);

        textViewIdOs    = (TextView) findViewById(R.id.txt_new_orc_IdOs);
        textViewCli     = (TextView) findViewById(R.id.txt_new_orc_Cliente);

        textViewIdOs.setText("O.S: " + detalhes.id_web);
        textViewCli.setText("Cliente: " + detalhes.nome);

        //Toast.makeText(this, idOs, Toast.LENGTH_LONG).show();

        rvProdutos = (RecyclerView) findViewById(R.id.lv_new_orc);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvProdutos.setHasFixedSize(true);

        // use a linear layout manager
        lmRecycler = new LinearLayoutManager(this);
        rvProdutos.setLayoutManager(lmRecycler);

        // specify an adapter (see also next example)
        adapter = new AdapterProduto(this,Produtos.detalhesProd(idOs));
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

    public void addIten (View view){
        Intent intent = null;
        intent = new Intent(this, AddItemOrc.class);
        //Envia a OS Clicada no RecyclerView
        Bundle params = new Bundle();
        params.putString("IdOs", idOs);
        intent.putExtras(params);
        startActivity(intent);
    }
}
