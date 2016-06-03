package com.webdroidteam.teste_layout_1.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.adapter.AdapterItemExecutar;
import com.webdroidteam.teste_layout_1.bodyExecutar.ExecutarOs;
import com.webdroidteam.teste_layout_1.models.Servicos;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;

import java.util.ArrayList;
import java.util.List;

public class Executar extends Fragment implements ClickListener{
    private RecyclerView rvExecutar;
    private AdapterItemExecutar adapter;
    private RecyclerView.LayoutManager lmRecycler;
    private Servicos servicos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }
        View rootView = inflater.inflate(R.layout.activity_executar,null);

        rvExecutar= (RecyclerView) rootView.findViewById(R.id.lvListaExec);
        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(getActivity());
        adapter = new AdapterItemExecutar(getActivity(), servicos.listaExecutar(usuarioPreferences.getIdUser()), this);

        lmRecycler = new LinearLayoutManager(getActivity());
        rvExecutar.setLayoutManager(lmRecycler);

        rvExecutar.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return rootView;
    }

    @Override
    public void onItemClick(Servicos servicos) {
        Intent intent = null;
        intent = new Intent(getContext(), ExecutarOs.class);
        //Envia a OS Clicada no RecyclerView
        Bundle params = new Bundle();
        params.putString("IdOs", servicos.getId_web());
        intent.putExtras(params);
        startActivity(intent);
    }
}
