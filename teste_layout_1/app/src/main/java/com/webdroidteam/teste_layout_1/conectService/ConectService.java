package com.webdroidteam.teste_layout_1.conectService;

import com.webdroidteam.teste_layout_1.models.ServiceCatalog;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leonardo on 06/04/2016.
 */
public interface ConectService {
    public static final String BASE_URL = "http://meugerente.esy.es/api/api.php/";

    @GET("usuarios")
    Call<ServiceCatalog> listCatalog();

    @GET("servicos/id")
    Call<ServiceCatalog> listServicosId(@Query("id_us") String id);
}
