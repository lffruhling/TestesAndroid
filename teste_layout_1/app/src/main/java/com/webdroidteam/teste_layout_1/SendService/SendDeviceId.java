package com.webdroidteam.teste_layout_1.SendService;

/**
 * Created by Leonardo on 05/05/2016.
 */
public class SendDeviceId {
    private String id_tec, device_id;

    public SendDeviceId(String id_tec, String device_id){
        this.id_tec = id_tec;
        this.device_id = device_id;
    }

    public String getId_tec() {
        return id_tec;
    }

    public void setId_tec(String id_tec) {
        this.id_tec = id_tec;
    }

    public String getId_device() {
        return device_id;
    }

    public void setId_device(String id_device) {
        this.device_id = id_device;
    }
}
