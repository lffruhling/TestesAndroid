package com.webdroidteam.teste_banco_2.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.webdroidteam.teste_banco_2.R;
import com.webdroidteam.teste_banco_2.model.Usuario;

import java.util.List;

/**
 * Created by Leonardo on 04/04/2016.
 */
public class UsuarioAdapter extends BaseAdapter {

    private Context context;
    private List<Usuario> lista;

    public UsuarioAdapter(Context ctx, List<Usuario> usuarios){
        this.context = ctx;
        this.lista = usuarios;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Usuario usuario = lista.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.usuarios, null);
        }

        TextView txtNome = (TextView) view.findViewById(R.id.txt_usuario_nome);
        txtNome.setText(usuario.getNome());

        return view;
    }
}
