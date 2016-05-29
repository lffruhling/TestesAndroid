package com.webdroidteam.teste_layout_1.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.bodyExecutar.ExecutarOs;
import com.webdroidteam.teste_layout_1.bodyOrcar.CriarOrcamento;

import java.util.ArrayList;
import java.util.List;

public class Executar extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View rootView = (LinearLayout) inflater.inflate(R.layout.activity_executar, container, false);

//        ServicosDAO servicosDAO = new ServicosDAO(getActivity());
//
//        List<String> atividades = new ArrayList<>(servicosDAO.listarExec());

        //String[] atividades = new String[]{"Executar 1", "Executar 2"};

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,atividades);

        ListView lv_lista = (ListView) rootView.findViewById(R.id.lvListaExec);

//        lv_lista.setAdapter(adapter);

        lv_lista.setOnItemClickListener(chamaAtividades());

        // Inflamos o layout executar.xml
        return rootView;
    }

    public AdapterView.OnItemClickListener chamaAtividades(){
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                Intent intent = null;
                intent = new Intent(getContext(), ExecutarOs.class);
                startActivity(intent);
            }
        });
    }
}
