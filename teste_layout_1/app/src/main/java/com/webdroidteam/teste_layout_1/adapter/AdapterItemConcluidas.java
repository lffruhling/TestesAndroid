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
import com.webdroidteam.teste_layout_1.models.Servicos;

import java.util.List;

/**
 * Created by Leonardo on 23/04/2016.
 */
public class AdapterItemConcluidas extends RecyclerView.Adapter<AdapterItemConcluidas.SimpleViewHolder> {

    private Context context;
    private List<Servicos> itens;
    private ClickListener clickListner;

    public AdapterItemConcluidas(Context context, List<Servicos> itens){
        this.itens = itens;
        this.context = context;
        this.clickListner = clickListner;
        Log.d("SQL", "total: "+itens.size());
    }


    //Infla as informações da view
    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_executar, parent, false);
        return new SimpleViewHolder(view);

    }

    //Seta as informações para o item
    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        final Servicos servicos = itens.get(position);
        Log.d("SQL", "serviço: "+servicos.id_web);
        holder.id_os.setText(servicos.id_web);
        holder.cliente.setText(servicos.nome);
        holder.servico.setText(servicos.serv);
        holder.data_serv.setText(servicos.data);
        //ativa onclick no item
//        holder.rlItemRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListner.onItemClick(servicos);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView id_os;
        TextView cliente;
        TextView servico;
        TextView data_serv;
        RelativeLayout rlItemRow;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            id_os = (TextView) itemView.findViewById(R.id.txt_item_exec_IdOs);
            cliente = (TextView) itemView.findViewById(R.id.txt_item_exec_cliente);
            servico = (TextView) itemView.findViewById(R.id.txt_item_exec_servico);
            data_serv = (TextView) itemView.findViewById(R.id.txt_item_exec_data);
            rlItemRow = (RelativeLayout) itemView.findViewById(R.id.rlItemRow);
        }
    }

}
