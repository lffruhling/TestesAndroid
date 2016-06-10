package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.models.NovoProdutoOrcado;
import com.webdroidteam.teste_layout_1.models.Produtos;

public class AddItemOrc extends AppCompatActivity {
    private TextView tVDesc, tVQuant;
    private String idOs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_orc);

        //Recebe id da os para buscar os dados
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        idOs = params.getString("IdOs");
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

    public void limpar(View view){
        tVDesc    = (TextView) findViewById(R.id.cpo_add_orc_desc);
        tVQuant     = (TextView) findViewById(R.id.cpo_add_orc_quant);

        tVDesc.setText("");
        tVQuant.setText("");
    }

    public void addNewProduto (View view){
        tVDesc    = (TextView) findViewById(R.id.cpo_add_orc_desc);
        tVQuant     = (TextView) findViewById(R.id.cpo_add_orc_quant);
        String desc = tVDesc.getText().toString().trim();
        String quant = tVQuant.getText().toString().trim();;
        if (desc.equals("") && quant.equals("")){
            Toast.makeText(this, "Adicione uma descrição e uma quantidade!", Toast.LENGTH_LONG).show();
        }else if (desc.equals("")){
            Toast.makeText(this, "Adicione uma Descição!", Toast.LENGTH_LONG).show();
        }else if(quant.equals("")){
            Toast.makeText(this, "A quantidade não pode ser menor que 1!", Toast.LENGTH_LONG).show();
        }else if(!(desc.equals(""))&&!(quant.equals(""))) {
            NovoProdutoOrcado novoProdutoOrcado = new NovoProdutoOrcado();
            novoProdutoOrcado.id_os = idOs;
            novoProdutoOrcado.desc = desc;
            novoProdutoOrcado.quant = quant;
            novoProdutoOrcado.save();
            Toast.makeText(this, "Produto adicionado com sucesso.", Toast.LENGTH_LONG).show();
            tVDesc.setText("");
            tVQuant.setText("");
        }
    }

    public void detalhesProdutosCadastrado (View view){
        Intent intent = null;
        intent = new Intent(this, NovoProdutoCadActivity.class);
        //Envia a OS Clicada no RecyclerView
        Bundle params = new Bundle();
        params.putString("IdOs", idOs);
        intent.putExtras(params);
        startActivity(intent);
    }
}
