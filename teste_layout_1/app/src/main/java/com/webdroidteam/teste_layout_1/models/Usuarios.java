package com.webdroidteam.teste_layout_1.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by Leonardo on 06/04/2016.
 *
 */
@Table(name="tb_usuario")
public class Usuarios extends Model{
    public Integer _id;
    @Expose
    public String nome;
    @Expose
    public String id_web;
    @Expose
    public String email;
    @Expose
    public String usuario;
    @Expose
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
        super();
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

    @Override
    public String toString() {
        return "Usuarios{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", id_web='" + id_web + '\'' +
                ", email='" + email + '\'' +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
