package com.webdroidteam.teste_layout_1.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.adapter.AdapterItemConcluidas;
import com.webdroidteam.teste_layout_1.adapter.AdapterItemExecutar;
import com.webdroidteam.teste_layout_1.models.Servicos;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;

import java.util.ArrayList;
import java.util.List;

public class Concluidas extends Fragment {
    private RecyclerView rvConcluidas;
    private AdapterItemConcluidas adapter;
    private RecyclerView.LayoutManager lmRecycler;
    private Servicos servicos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }
        View rootView = inflater.inflate(R.layout.activity_concluidas,null);

        rvConcluidas = (RecyclerView) rootView.findViewById(R.id.lvListaConc);
        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(getActivity());
        adapter = new AdapterItemConcluidas(getActivity(), servicos.listaConcluidas(usuarioPreferences.getIdUser()));

        lmRecycler = new LinearLayoutManager(getActivity());
        rvConcluidas.setLayoutManager(lmRecycler);

        rvConcluidas.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return rootView;
    }
}
