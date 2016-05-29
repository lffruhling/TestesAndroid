package com.webdroidteam.teste_layout_1.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.adapter.AdapterItemOrcar;
import com.webdroidteam.teste_layout_1.bodyOrcar.CriarOrcamento;
import com.webdroidteam.teste_layout_1.models.Servicos;
import com.webdroidteam.teste_layout_1.models.Usuarios;
import com.webdroidteam.teste_layout_1.preferences.UsuarioPreferences;
import com.webdroidteam.teste_layout_1.util.Mensagem;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Orcar extends Fragment implements ClickListener {

    private RecyclerView rvOrcar;
    private AdapterItemOrcar adapter;
    private RecyclerView.LayoutManager lmRecycler;
    private Servicos servicos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.activity_orcar,null);

        rvOrcar = (RecyclerView) rootView.findViewById(R.id.lvListaOrc);
        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(getActivity());
        adapter = new AdapterItemOrcar(getActivity(), servicos.listaOrcar(usuarioPreferences.getIdUser()), this);

        lmRecycler = new LinearLayoutManager(getActivity());
        rvOrcar.setLayoutManager(lmRecycler);

        rvOrcar.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return rootView;

    }

    @Override
    public void onItemClick(Servicos servicos) {
        Intent intent = null;
        intent = new Intent(getContext(), CriarOrcamento.class);
        startActivity(intent);
    }
}
