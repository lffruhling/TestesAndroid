package com.webdroidteam.teste_layout_1.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.bodyOrcar.CriarOrcamento;
import com.webdroidteam.teste_layout_1.util.Mensagem;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Orcar extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        if (container == null) {
            return null;
        }

        View rootView = (LinearLayout) inflater.inflate(R.layout.activity_orcar, container, false);

//        ServicosDAO servicosDAO = new ServicosDAO(getActivity());
//
//        List<String> atividades = new ArrayList<>(servicosDAO.listarOrcar());

        //String[] atividades = new String[]{"orc 1","orc 2"};

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,atividades);

        ListView lv_lista = (ListView) rootView.findViewById(R.id.lvListaOrc);

//        lv_lista.setAdapter(adapter);

        lv_lista.setOnItemClickListener(chamaAtividades());

        // Inflamos o layout Orcar.xml
        return rootView;
    }

    public AdapterView.OnItemClickListener chamaAtividades(){
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                Intent intent = null;
                intent = new Intent(getContext(), CriarOrcamento.class);
                startActivity(intent);
            }
        });
    }

    /*Código do Menu*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_filtro:
                //text = page.getText().toString();
                //speakOut(text);
                // Do Activity menu item stuff here
                return true;
            case R.id.action_menu_sinc:
                //speakOf();
                // Not implemented here
                return true;
            default:
                break;
        }

        return false;
    }

}
