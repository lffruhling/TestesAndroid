package com.webdroidteam.teste_layout_1.conectService;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdroidteam.teste_layout_1.preferences.Preferences;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leonardo on 18/05/2016.
 */
public class ApiFactory {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String BRAZILIAN_DATE_FORMAT = "dd/MM/yyyy";
    public static final String VERSION_API = "v1/";
    public static final String API_KEY = "apiKey ";
    private static Preferences preferencesKey;
    private static <T> T create(Class<T> endpoint) {
        return create(endpoint, DEFAULT_DATE_FORMAT);
    }
//    private static <T> T createAutenticate(Class<T> endpoint) {
//        return createAutenticate(endpoint, DEFAULT_DATE_FORMAT);
//    }
    private static OkHttpClient client = null;

    private static <T> T create(Class<T> endpoint, String serializeDateFormat) {
        Gson gson = buildGson(serializeDateFormat);
        client = new OkHttpClient();
        Retrofit retrofit = buildRetroFit(client, gson);
        return retrofit.create(endpoint);
    }
//    private static <T> T createAutenticate(Class<T> endpoint, String serializeDateFormat) {
//        preferencesKey = new Preferences(EasyServApp.getInstance());
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        client = new OkHttpClient.Builder()
//                .addInterceptor((new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        Request newRequest = request.newBuilder().addHeader(API_KEY,preferencesKey.getAPiKey()).build();
//
//                        return chain.proceed(newRequest);
//                    }
//                })).addInterceptor(logging)
//
//                .build();
//
//        Retrofit retrofit = buildRetroFit(client,buildGson(serializeDateFormat));
//
//        return retrofit.create(endpoint);
//    }
    private static retrofit2.Retrofit buildRetroFit(OkHttpClient client, Gson gson) {

        return  new Retrofit.Builder()
                .baseUrl(ConectService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


//        return new Retrofit.Builder()
//                .baseUrl("http://dev.easy-serv.com/api/v1/")
//                .client(client)
//                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
//                .addConvrterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
    }

    public static Gson buildGson() {
        return buildGson(DEFAULT_DATE_FORMAT);
    }

//    public static <T> T exceptionParser(Class<T> tClass, HttpException error) {
//        return new HttpExceptionParser<T>(buildGson(), tClass).toResponse(error);
//    }

    private static Gson buildGson(String serializeDateFormat){
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer(serializeDateFormat))
                .registerTypeAdapter(Date.class, new MixedDateDeserializer(DEFAULT_DATE_FORMAT,
                        BRAZILIAN_DATE_FORMAT))

                .create();
    }

//    public static ServiceOrderApi serviceOrderApi(){
//        return createAutenticate(ServiceOrderApi.class);
//    }
//
//    public static AuthApi authApi(){
//        return create(AuthApi.class);
//    }
//
//    public static UserApi userApi(){return createAutenticate(UserApi.class);}
//}
    public static ConectService conectService(){
        return  create(ConectService.class);
    }
}
