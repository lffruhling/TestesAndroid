package com.webdroidteam.teste_layout_1.models;

import java.util.List;

/**
 * Created by Leonardo on 17/04/2016.
 */
public class Servicos {
    public Integer _id;
    public String id_web;
    public String nome;
    public String serv;
    public String colab;
    public String obs;
    public String orc;
    public String fot;
    public String data;

    public List<Produtos> produtos;

    public Servicos(Integer id, String id_web, String nome, String serv, String colab, String obs, String orc, String fot, String data, List<Produtos> produtos){
        this._id = id;
        this.id_web = id_web;
        this.nome = nome;
        this.serv = serv;
        this.colab = colab;
        this.obs = obs;
        this.orc = orc;
        this.fot = fot;
        this.data = data;
        this.produtos = produtos;
    }

    public  Servicos(){

    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getId_web() {
        return id_web;
    }

    public void setId_web(String id_web) {
        this.id_web = id_web;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public String getColab() {
        return colab;
    }

    public void setColab(String colab) {
        this.colab = colab;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getOrc() {
        return orc;
    }

    public void setOrc(String orc) {
        this.orc = orc;
    }

    public String getFot() {
        return fot;
    }

    public void setFot(String fot) {
        this.fot = fot;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
}
