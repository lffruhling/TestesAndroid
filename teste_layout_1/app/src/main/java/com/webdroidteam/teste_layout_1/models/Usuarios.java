package com.webdroidteam.teste_layout_1.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by Leonardo on 06/04/2016.
 */
@Table(name = "Usuarios")
public class Usuarios extends Model{

    @Expose
    @Column(name = "nome")
    public String nome;

    @Expose
    @Column(name = "id_web")
    public String id_web;

    @Expose
    @Column(name = "email")
    public String email;

    @Expose
    @Column(name = "usuario")
    public String usuario;

    @Expose
    @Column(name = "senha")
    public String senha;

    public Usuarios(String nome, String id_web, String email, String usuario, String senha){
        super();
        this.nome = nome;
        this.id_web = id_web;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;

    }

    public  Usuarios(){
        super();
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
