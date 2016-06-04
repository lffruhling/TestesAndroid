package com.webdroidteam.teste_layout_1.conectService;

import com.google.android.gms.common.data.DataBufferObserver;
import com.webdroidteam.teste_layout_1.SendService.RCadId;
import com.webdroidteam.teste_layout_1.SendService.SendDeviceId;
import com.webdroidteam.teste_layout_1.SendService.SendExecutadas;
import com.webdroidteam.teste_layout_1.SendService.SendOrcadas;
import com.webdroidteam.teste_layout_1.models.ServiceCatalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Leonardo on 06/04/2016.
 */
public interface ConectService {
    public static final String BASE_URL = "http://meugerente.esy.es/api/api.php/";

    @GET("usuarios")
    Observable<ServiceCatalog> listCatalog();

    @GET("servicos/id")
    Observable<ServiceCatalog> listServicosId(@Query("id_us") String id);

    @POST("device/registration")
    Call<SendDeviceId> postIdDevice(@Body SendDeviceId sendDeviceId);

    @POST("os/finaliza/exec")
    Call<SendExecutadas> postExecutas(@Body SendExecutadas sendExecutadas);

    @POST("os/finaliza/orc")
    Call<SendOrcadas> postOrcadas(@Body SendOrcadas sendOrcadas);
}
