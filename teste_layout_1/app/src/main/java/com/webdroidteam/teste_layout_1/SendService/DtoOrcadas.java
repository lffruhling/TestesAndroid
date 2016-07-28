package com.webdroidteam.teste_layout_1.SendService;

import java.util.List;

/**
 * Created by Leonardo on 05/05/2016.
 */
public class DtoOrcadas {
    public String id_os;
    public String ass_mob;
    public List<ProdutosMob> produtos_mobs;

    public DtoOrcadas(String id_os, String ass_mob, List<ProdutosMob> produtos_mobs) {
        this.id_os = id_os;
        this.ass_mob = ass_mob;
        this.produtos_mobs = produtos_mobs;
    }
}
