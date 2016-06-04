package com.webdroidteam.teste_layout_1.bodyExecutar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.adapter.AdapterItemExecutar;
import com.webdroidteam.teste_layout_1.adapter.AdapterProduto;
import com.webdroidteam.teste_layout_1.models.Produtos;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;

public class ProdutosActivity extends Activity {
    private String idOs;
    private RecyclerView rvProdutos;
    private AdapterProduto adapter;
    private RecyclerView.LayoutManager lmRecycler;

    private Produtos produtos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_os);

        //Recebe id da os para buscar os dados
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        idOs = params.getString("IdOs");
        //Toast.makeText(this, idOs, Toast.LENGTH_LONG).show();
        produtos.detalhesProd(idOs);

        rvProdutos = (RecyclerView) findViewById(R.id.lv_prod_os);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvProdutos.setHasFixedSize(true);

        // use a linear layout manager
        lmRecycler = new LinearLayoutManager(this);
        rvProdutos.setLayoutManager(lmRecycler);

        // specify an adapter (see also next example)
        adapter = new AdapterProduto(this,Produtos.detalhesProd(idOs));

        if(adapter.getItemCount() < 1){
            Intent nIntent = null;
            nIntent = new Intent(this, FotosOs.class);
            //Envia a OS Clicada no RecyclerView
            Bundle nParams = new Bundle();
            nParams.putString("IdOs", idOs);
            nIntent.putExtras(nParams);
            startActivity(nIntent);
            finish();
        }
        rvProdutos.setAdapter(adapter);
    }



    public void  voltar (View view){
        finish();
    }

    public void fotos_os(View view){

        Intent intent = null;
        intent = new Intent(this, FotosOs.class);
        //Envia a OS Clicada no RecyclerView
        Bundle params = new Bundle();
        params.putString("IdOs", idOs);
        intent.putExtras(params);
        startActivity(intent);

//        startActivity(new Intent(this, FotosOs.class));
        finish();
    }
}
