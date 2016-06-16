package com.webdroidteam.teste_layout_1.preferences;

import android.content.Context;

/**
 * Created by Leonardo on 18/05/2016.
 */
public class UsuarioPreferences extends Preferences{

    public UsuarioPreferences(Context context){
        super(context);
    }

    private enum USER{
        ID_USER,
        TOKEN,
        OS_USER
    }

    public void setIdUser(String idUser){
        this.<String>setValue(USER.ID_USER.toString(),idUser);
    }
    public String getIdUser(){
        return this.<String>getValue(USER.ID_USER.toString(), "");
    }

    public void setToken(String token){
        this.<String>setValue(USER.TOKEN.toString(),token);
    }

    public String getToken(){
        return this.<String>getValue(USER.TOKEN.toString(), "");
    }

    public void setOsUser(String OsUser){
        this.<String>setValue(USER.OS_USER.toString(),OsUser);
    }

    public String getOsUser(){
        return this.<String>getValue(USER.OS_USER.toString(),"");
    }
}
