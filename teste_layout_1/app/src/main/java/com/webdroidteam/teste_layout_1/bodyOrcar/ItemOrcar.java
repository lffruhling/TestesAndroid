package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.content.ClipData;

/**
 * Created by Leonardo on 23/04/2016.
 */
public class ItemOrcar {
    private String texto;
    private int iconeRid;

    public ItemOrcar(){
        this ("",-1);
    }

    public ItemOrcar (String texto, int iconeRid){
        this.texto = texto;
        this.iconeRid = iconeRid;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }
}
