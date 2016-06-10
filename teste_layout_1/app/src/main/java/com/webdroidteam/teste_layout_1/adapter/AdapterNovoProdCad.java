package com.webdroidteam.teste_layout_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.models.NovoProdutoOrcado;

import java.security.PrivateKey;
import java.util.List;

/**
 * Created by Leonardo on 10/06/2016.
 */
public class AdapterNovoProdCad extends RecyclerView.Adapter<AdapterNovoProdCad.SimpleViewHolder>{
    private Context context;
    private List<NovoProdutoOrcado> itens;

    public AdapterNovoProdCad(Context context, List<NovoProdutoOrcado> itens) {
        this.context = context;
        this.itens = itens;
    }

    @Override
    public AdapterNovoProdCad.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_produto_os, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterNovoProdCad.SimpleViewHolder holder, int position) {
        final  NovoProdutoOrcado novoProdutoOrcado = itens.get(position);
        holder.desc.setText(novoProdutoOrcado.desc);
        holder.quantidade.setText(novoProdutoOrcado.quant);
        holder.grupo.setVisibility(View.GONE);
        holder.grupoLabel.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public static class SimpleViewHolder extends  RecyclerView.ViewHolder{
        TextView desc;
        TextView quantidade;
        TextView grupo;
        TextView grupoLabel;
        RelativeLayout rlItemRow;
        public SimpleViewHolder(View itemView){
            super(itemView);
            desc = (TextView) itemView.findViewById(R.id.txt_item_prod_os_prod);
            quantidade = (TextView) itemView.findViewById(R.id.txt_item_prod_quant);
            grupo = (TextView) itemView.findViewById(R.id.txt_item_prod_os_grupo);
            grupoLabel = (TextView) itemView.findViewById(R.id.txt_item_prod_group);
            rlItemRow = (RelativeLayout) itemView.findViewById(R.id.rlItemRow);
        }
    }
}
