package com.webdroidteam.teste_layout_1.models;

/**
 * Created by Leonardo on 06/04/2016.
 */
public class Usuarios {
    public Integer _id;
    public String nome;
    public String id_web;
    public String email;
    public String usuario;
    public String senha;


    public Usuarios(int id, String nome, String id_web, String email, String usuario, String senha){
        this._id = id;
        this.nome = nome;
        this.id_web = id_web;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;

    }

    public  Usuarios(){

    }


    public Integer get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId_web() {
        return id_web;
    }

    public void setId_web(String id_web) {
        this.id_web = id_web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
