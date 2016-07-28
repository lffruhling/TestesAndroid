package com.webdroidteam.teste_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Leonardo on 19/05/2016.
 */
public class AdapterLivros extends BaseAdapter {
    private Context context;
    private ArrayList<Livro> livros;

    public AdapterLivros(Context context, ArrayList<Livro> livros){
        this.context = context;
        this.livros = livros;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int position) {
        return livros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.lv_item, null);

        if(view != null){
            ImageView img = (ImageView) view.findViewById(R.id.img_foto);
            TextView title = (TextView) view.findViewById(R.id.txt_titulo);
            TextView desc = (TextView) view.findViewById(R.id.txt_desc);

            Livro livro = livros.get(position);
            img.setImageResource(livro.icon);
            title.setText(livro.titulo);
            desc.setText(livro.descricao);

        }

        return view;
    }
}
