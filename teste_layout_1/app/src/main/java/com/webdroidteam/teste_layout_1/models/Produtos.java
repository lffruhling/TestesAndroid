package com.webdroidteam.teste_layout_1.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Leonardo on 17/04/2016.
 */
@Table(name = "Produtos")
public class Produtos extends Model{

    @Expose
    @Column(name = "id_pro")
    public String id_pro;

    @Expose
    @Column(name = "id_os")
    public String id_os;

    @Expose
    @Column(name = "nome")
    public String nome;

    @Expose
    @Column(name = "desc")
    public String desc;

    @Expose
    @Column(name = "quant")
    public String quant;

    public Produtos(String id_pro, String id_os, String nome, String desc, String quant){
        super();
        this.id_pro = id_pro;
        this.id_os = id_os;
        this.nome = nome;
        this.desc = desc;
        this.quant = quant;

    }

    public  Produtos(){
        super();
    }

    public String getId_os() {
        return id_os;
    }

    public void setId_os(String id_os) {
        this.id_os = id_os;
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
