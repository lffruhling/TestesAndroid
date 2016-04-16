package com.webdroidteam.teste_retrofit_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.webdroidteam.teste_retrofit_3.models.Produtos;
import com.webdroidteam.teste_retrofit_3.models.Servicos;
import com.webdroidteam.teste_retrofit_3.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TESTEEEEE: --- ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(UdacityService.BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        UdacityService service = retrofit.create(UdacityService.class);
        //Call<UdacityCatalog> requestCatalog = service.listCatalog();

        /*requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"ErroRRR de insucesso: "+response.code());
                }else{
                    //Requisição com sucesso
                    UdacityCatalog catalog = response.body();
                    Integer i = 0;
                    for(Clientes c : catalog.clientes){
                        Log.i("OKKKKKK",String.format("%s: %s",c.id,c.nome));
                        i++;
                        Log.i("Array: ", i.toString());
                        Log.i(TAG,"---------------------");
                    }
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e(TAG,"--------------Erro: "+t.getMessage());
            }
        });*/

        /*
        *
        *Código para list View
        */

        String[] atividades = new String[]{"Atividade 1", "Atividade 2"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,atividades);

        ListView lv_lista = (ListView)findViewById(R.id.lvLista);

        lv_lista.setAdapter(adapter);

        lv_lista.setOnItemClickListener(chamaAtividades());

        // Teste 14/04/ - enviar json
        Call<UdacityCatalog> call = service.listServicos();

        call.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"JSON ERROR: "+response.code());
                }else{
                    //Requisição com sucesso
                    Log.i(TAG,"JSON OK: "+response.code());
                    UdacityCatalog catalog = response.body();

                    for(Servicos s : catalog.servicos){
                        Log.i("RES",String.format("%s : %s", s.id_web,s.nome));

                        for(Produtos p : s.produtos){
                            Log.i("RES",p.nome);
                        }

                        Log.i("RES","--------------");
                    }
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e(TAG,"FAILURE: "+t.getMessage());
            }
        });
    }

    public AdapterView.OnItemClickListener chamaAtividades(){
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(getBaseContext(), atividade1.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getBaseContext(), atividade2.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
    /*public void acessarAtividade1(View view){
        Intent intent = new Intent(this, atividade1.class);
        startActivity(intent);
    }

    public void acessarAtividade2(View view){
        Intent intent = new Intent(this, atividade2.class);
        startActivity(intent);
    }*/

    public void sair(View view){
        finish();
    }
}
