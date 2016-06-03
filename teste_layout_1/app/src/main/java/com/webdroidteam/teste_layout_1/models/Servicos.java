package com.webdroidteam.teste_layout_1.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Leonardo on 17/04/2016.
 */
@Table(name = "Servicos")
public class Servicos extends Model{

    @Expose
    @Column(name = "id_web")
    public String id_web;

    @Expose
    @Column(name = "nome")
    public String nome;

    @Expose
    @Column(name = "id_colab")
    public String id_colab;

    @Expose
    @Column(name = "serv")
    public String serv;

    @Expose
    @Column(name = "colab")
    public String colab;

    @Expose
    @Column(name = "obs")
    public String obs;

    @Expose
    @Column(name = "orc")
    public String orc;

    @Expose
    @Column(name = "fot")
    public String fot;

    @Expose
    @Column(name = "data")
    public String data;

    @Expose
    @Column(name = "end")
    public String end;

    @Expose
    @Column(name = "nro")
    public String nro;

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getConcluida() {
        return concluida;
    }

    public void setConcluida(String concluida) {
        this.concluida = concluida;
    }

    @Column(name = "concluida")
    public String concluida;

    @Expose
    public List<Produtos> produtos;

    public Servicos(String id_web, String nome, String id_colab, String serv, String colab, String obs, String orc, String fot, String data, List<Produtos> produtos){
        super();
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

    public static Servicos validaServicos(String id_web){
        return new Select().from(Servicos.class).where("id_web = ?", id_web).executeSingle();
    }

    public static Servicos limpaBanco(){
        new Delete().from(Servicos.class).where("Id >= ?", 1).execute();
        return null;
    }

    public static List<Servicos> listaConcluidas(String id_tec){
        return new Select().
                from(Servicos.class).
                where("id_colab = ? and concluida = 1", id_tec).
                orderBy("Id ASC").
                execute();
    }

    public static List<Servicos> listaOrcar(String id_tec){
        return new Select().
                from(Servicos.class).
                where("id_colab = ? and orc = 1 and (concluida isnull or concluida = 0)", id_tec).
                execute();
    }

    public static List<Servicos> listaExecutar(String id_tec){
        return new Select().
                from(Servicos.class).
                where("id_colab = ? and orc = 0 and (concluida isnull or concluida = 0)", id_tec).
                execute();
    }

    public static Servicos detalhesOs(String id_os_web){
        return new Select()
                .from(Servicos.class)
                .where("id_web = ?", id_os_web)
                .executeSingle();
    }
}
