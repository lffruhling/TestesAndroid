package com.webdroidteam.teste_layout_1.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.webdroidteam.teste_layout_1.R;

public class Concluidas extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View rootView = (LinearLayout) inflater.inflate(R.layout.activity_concluidas, container, false);

        String[] atividades = new String[]{"Concluídas 1", "Concluídas 2"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,atividades);

        ListView lv_lista = (ListView) rootView.findViewById(R.id.lvListaConc);

        lv_lista.setAdapter(adapter);

        // Inflamos o layout concluidas.xml
        return rootView;
    }
}
