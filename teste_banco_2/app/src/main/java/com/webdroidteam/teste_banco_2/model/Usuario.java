package com.webdroidteam.teste_banco_2.model;

/**
 * Created by Leonardo on 02/04/2016.
 */
public class Usuario {
    private Integer _id;
    private String nome;
    private String login;
    private String senha;
    private String created_ad;


    public Usuario(int id, String nome, String login, String senha, String created_ad){
        this._id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.created_ad = created_ad;

    }

    public  Usuario(){

    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) { this.senha = senha;  }

    public String getCreated_ad() {
        return created_ad;
    }

    public void setCreated_ad(String created_ad) {
        this.created_ad = created_ad;
    }

}
