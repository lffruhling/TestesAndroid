package com.webdroidteam.teste_layout_1.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Leonardo on 09/06/2016.
 */
@Table(name = "NovoProdutoOrcado")
public class NovoProdutoOrcado extends Model {
    @Column(name = "id_os")
    public String id_os;

    @Column(name = "desc")
    public String desc;

    @Column(name = "quant")
    public String quant;

    public NovoProdutoOrcado(String id_os, String desc, String quant) {
        this.id_os = id_os;
        this.desc = desc;
        this.quant = quant;
    }

    public  NovoProdutoOrcado(){
        super();
    }

    public String getId_os() {
        return id_os;
    }

    public void setId_os(String id_os) {
        this.id_os = id_os;
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

    public static List<NovoProdutoOrcado> detalhesNovoProdcad(String id_os_web){
        return new Select()
                .from(NovoProdutoOrcado.class)
                .where("id_os = ?", id_os_web)
                .execute();
    }

    public static NovoProdutoOrcado deletaProdCad(String id){
        new Delete().from(NovoProdutoOrcado.class).where("Id = ?", id).execute();
        return null;
    }

}
