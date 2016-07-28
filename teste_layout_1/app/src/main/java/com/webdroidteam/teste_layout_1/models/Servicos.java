package com.webdroidteam.teste_layout_1.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Leonardo on 17/04/2016.
 */
@Table(name = "tb_os")
public class Servicos extends Model {
    public Integer _id;
    @Expose
    public String id_web;
    @Expose
    public String nome;
    @Expose
    public String id_colab;
    @Expose
    public String serv;
    @Expose
    public String colab;
    @Expose
    public String obs;
    @Expose
    public String orc;
    @Expose
    public String fot;
    @Expose
    public String data;

    public List<Produtos> produtos;

    public Servicos(Integer id, String id_web, String nome, String id_colab, String serv, String colab, String obs, String orc, String fot, String data, List<Produtos> produtos){
        this._id = id;
        this.id_web = id_web;
        this.nome = nome;
        this.id_colab = id_colab;
        this.serv = serv;
        this.colab = colab;
        this.obs = obs;
        this.orc = orc;
        this.fot = fot;
        this.data = data;
        this.produtos = produtos;
    }

    public  Servicos(){
        super();
    }

    public String getId_colab() {
        return id_colab;
    }

    public void setId_colab(String id_colab) {
        this.id_colab = id_colab;
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

    @Override
    public String toString() {
        return "Servicos{" +
                "_id=" + _id +
                ", id_web='" + id_web + '\'' +
                ", nome='" + nome + '\'' +
                ", id_colab='" + id_colab + '\'' +
                ", serv='" + serv + '\'' +
                ", colab='" + colab + '\'' +
                ", obs='" + obs + '\'' +
                ", orc='" + orc + '\'' +
                ", fot='" + fot + '\'' +
                ", data='" + data + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}


