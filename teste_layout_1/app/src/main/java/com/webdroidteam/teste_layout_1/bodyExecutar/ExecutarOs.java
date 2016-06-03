package com.webdroidteam.teste_layout_1.bodyExecutar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.models.Servicos;

public class ExecutarOs extends AppCompatActivity {
    private Servicos servicos;
    private Servicos detalhes;
    private TextView textViewIdOs, textViewCli, textViewServ, textViewEnd, textViewNro, textViewObs;
    public String idOs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executar_os);

        //Recebe id da os para buscar os dados
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        idOs = params.getString("IdOs");
        //Toast.makeText(this, idOs, Toast.LENGTH_LONG).show();
        detalhes = servicos.detalhesOs(idOs);

        textViewIdOs    = (TextView) findViewById(R.id.txt_exec_os_IdOs);
        textViewCli     = (TextView) findViewById(R.id.txt_exec_os_Cliente);
        textViewServ    = (TextView) findViewById(R.id.txt_exec_os_Servico);
        textViewEnd     = (TextView) findViewById(R.id.txt_exec_os_end);
        textViewNro     = (TextView) findViewById(R.id.txt_exec_os_nro);
        textViewObs     = (TextView) findViewById(R.id.txt_exec_os_obs);

        textViewIdOs.setText("O.S: " + detalhes.id_web);
        textViewCli.setText("Cliente: " + detalhes.nome);
        textViewServ.setText("Serviço: " + detalhes.serv);
        textViewEnd.setText("End.: " + detalhes.end);
        textViewObs.setText("Obs.: "+ detalhes.obs);
    }

    public void  voltar (View view){
        finish();
    }

    public void iniciar_os(View view){
        Intent intent = null;
        intent = new Intent(this, ProdutosActivity.class);
        //Envia a OS Clicada no RecyclerView
        Bundle params = new Bundle();
        params.putString("IdOs", idOs);
        intent.putExtras(params);
        startActivity(intent);

        /*startActivity(new Intent(this, ProdutosActivity.class));*/
        finish();
    }
}
