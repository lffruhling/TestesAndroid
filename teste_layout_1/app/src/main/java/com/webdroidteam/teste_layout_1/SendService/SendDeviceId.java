package com.webdroidteam.teste_layout_1.SendService;

/**
 * Created by Leonardo on 05/05/2016.
 */
public class SendDeviceId {
    private String id_tec, id_device;

    public SendDeviceId(String id_tec, String id_device){
        this.id_tec = id_tec;
        this.id_device = id_device;
    }

    public String getId_tec() {
        return id_tec;
    }

    public void setId_tec(String id_tec) {
        this.id_tec = id_tec;
    }

    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
        this.id_device = id_device;
    }
}
