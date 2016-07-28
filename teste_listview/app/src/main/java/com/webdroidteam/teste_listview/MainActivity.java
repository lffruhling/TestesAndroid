package com.webdroidteam.teste_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.lv_lista);

        ArrayList<Livro> livros = new ArrayList<Livro>();

        for (int i = 0 ; i <10; i++){
            livros.add(new Livro());

            AdapterLivros adapterLivros = new AdapterLivros(this, livros);
            lv.setAdapter(adapterLivros);
        }
    }
}
