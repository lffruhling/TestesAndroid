package com.webdroidteam.teste_layout_1.models;

import java.util.List;

/**
 * Created by Leonardo on 06/04/2016.
 */
public class ServiceCatalog {
    public List<Usuarios> usuarios;
    public List<Servicos> servicos;

    @Override
    public String toString() {
        return "ServiceCatalog{" +
                "usuarios=" + usuarios +
                ", servicos=" + servicos +
                '}';
    }
}
