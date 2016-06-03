package com.webdroidteam.teste_layout_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.fragments.ClickListener;
import com.webdroidteam.teste_layout_1.models.Produtos;
import com.webdroidteam.teste_layout_1.models.Servicos;

import java.util.List;

/**
 * Created by Leonardo on 03/06/2016.
 */
public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.SimpleViewHolder> {
    private Context context;
    private List<Produtos> itens;

  /*  public AdapterProduto(Context context, List<Produtos> itens){
        this.itens = itens;
        this.context = context;
        Log.d("SQL", "total: "+itens.size());
    }*/

    public AdapterProduto(Context context, List<Produtos> itens) {
        context = context;
        this.itens = itens;
    }

    //Infla as informações da view
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_produto_os, parent, false);
        return new SimpleViewHolder(view);

    }

    //Seta as informações para o item
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        final Produtos produtos = itens.get(position);
//        Log.d("SQL", "serviço: "+produtos.id_web);
        holder.produto.setText(produtos.nome);
        holder.grupo.setText(produtos.desc);
        holder.quantidade.setText(produtos.quant);
    }

    public int getItemCount() {
        return itens.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView produto;
        TextView grupo;
        TextView quantidade;
        RelativeLayout rlItemRow;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            produto = (TextView) itemView.findViewById(R.id.txt_item_prod_os_prod);
            grupo = (TextView) itemView.findViewById(R.id.txt_item_prod_os_grupo);
            quantidade = (TextView) itemView.findViewById(R.id.txt_item_prod_quant);
            rlItemRow = (RelativeLayout) itemView.findViewById(R.id.rlItemRow);
        }
    }

}
