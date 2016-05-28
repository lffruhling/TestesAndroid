package com.webdroidteam.teste_layout_1.adapter;

import android.content.Context;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.dao.ServicosDAO;
import com.webdroidteam.teste_layout_1.models.Servicos;

import java.util.ArrayList;

/**
 * Created by Leonardo on 26/05/2016.
 */
public class AdapterExecutar extends BaseAdapter {
    private Context context;
    private ArrayList<Servicos> servicos;

    public AdapterExecutar(Context context, ArrayList<Servicos> servicos){
        this.context = context;
        this.servicos = servicos;
    }
    @Override
    public int getCount() {
        return servicos.size();
    }

    @Override
    public Object getItem(int position) {
        return servicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_item_executar, null);

        if (view != null){
            TextView os = (TextView) view.findViewById(R.id.txt_item_exec_IdOs);
            TextView cliete = (TextView) view.findViewById(R.id.txt_item_exec_cliente);
            TextView tServico = (TextView) view.findViewById(R.id.txt_item_exec_servico);
            TextView data = (TextView) view.findViewById(R.id.txt_item_exec_data);
            TextView obs = (TextView) view.findViewById(R.id.txt_item_exec_obs);

            Servicos servico = servicos.get(position);
            os.setText(servico.id_web);
            cliete.setText(servico.nome);
            tServico.setText(servico.serv);
            data.setText(servico.data);
            obs.setText(servico.obs);
        }
        return view;
    }
}
