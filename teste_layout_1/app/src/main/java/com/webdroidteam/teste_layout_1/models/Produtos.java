package com.webdroidteam.teste_layout_1.models;

import java.util.List;

/**
 * Created by Leonardo on 17/04/2016.
 */
public class Produtos {
    public Integer _id;
    public String id_pro;
    public String nome;
    public String desc;
    public String quant;

    public Produtos(Integer id, String id_pro, String nome, String desc, String quant){
        this._id = id;
        this.id_pro = id_pro;
        this.nome = nome;
        this.desc = desc;
        this.quant = quant;

    }

    public  Produtos(){

    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getId_pro() {
        return id_pro;
    }

    public void setId_pro(String id_pro) {
        this.id_pro = id_pro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }
}
