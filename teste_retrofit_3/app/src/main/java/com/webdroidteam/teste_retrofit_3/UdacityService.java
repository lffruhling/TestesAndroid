package com.webdroidteam.teste_retrofit_3;

import com.webdroidteam.teste_retrofit_3.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Leonardo on 26/03/2016.
 */
public interface UdacityService {

    public static final String BASE_URL = "http://infomaster.pe.hu/api/api.php/";

    @GET("clientes")
    Call<UdacityCatalog> listCatalog();

    @GET("servicos")
    Call<UdacityCatalog> listServicos();

    @GET("servicos/id")
    Call<UdacityCatalog> listServicosId(@Query("id_us") String id);

}
