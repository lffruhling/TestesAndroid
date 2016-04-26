package com.webdroidteam.teste_layout_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.bodyOrcar.ItemOrcar;

import java.util.List;

/**
 * Created by Leonardo on 23/04/2016.
 */
public class AdapterItemOrcar extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ItemOrcar> itens;

    public  AdapterItemOrcar (Context context, List<ItemOrcar> itens){
        //Itens do ListView
        this.itens = itens;
        //Objeto responsável por pegar o Layout do item
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        //retorna a quantidade de itens da lista
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        /*
        retornar o item da lista de acordo com sua posição
        Leia mais em: Android: Criando um ListView Customizado http://www.devmedia.com.br/android-criando-um-listview-customizado/26260#ixzz46keG9VTA
        */
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        /*
        irá retornar o id do item de acordo com sua posição

        Leia mais em: Android: Criando um ListView Customizado http://www.devmedia.com.br/android-criando-um-listview-customizado/26260#ixzz46kePEnTe
        * */
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        /* Se a view estiver nula (nunca criada), inflamos o layout nela*/
        if(view == null){
            /*infla o layout para podermos pegar as views*/
            view = mInflater.inflate(R.layout.item_orcar, null);

            /*
            * Criar um item de suporte para não precisarmos sempre
            * inflar as mesmas informações
            * */

            itemHolder = new ItemSuporte();
            itemHolder.txtTitle = ((TextView) view.findViewById(R.id.txt_item_IdOs));
            itemHolder.imgIcon = ((ImageView) view.findViewById(R.id.imgLogoOrc));

            /* Define os itens na view */
            view.setTag(itemHolder);
        }else{
            /* se a view já existe pega os itens */
            itemHolder = (ItemSuporte) view.getTag();
        }

        /*
        * Pega os dados da lista
        * e define os valores nos itens
        *
        * esse item orçar pode estar errado e dar erro aqui
        * */
        ItemOrcar item = itens.get(position);
        itemHolder.txtTitle.setText(item.getTexto());
        itemHolder.imgIcon.setImageResource(item.getIconeRid());

        /*retorna a view com as informações*/

        return view;
    }

    /*
    * Classe de suporte para os itens do layout
    * */
    private class ItemSuporte{
        ImageView imgIcon;
        TextView txtTitle;
    }
}
