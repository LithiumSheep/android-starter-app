package com.lithium.apitutorial.network;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.lithium.apitutorial.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public final class HttpClient {

    private static LoggingInterceptor prettyLoggger() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .tag("Network")
                .build();
    }

    @SuppressWarnings("ConstantConditions")
    private static HttpLoggingInterceptor basicLogger() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        return logger;
    }

    private static OkHttpClient client;
    private static synchronized OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(basicLogger())
                    .build();
        }
        return client;
    }

    private static Retrofit retrofit;
    private static synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    private static Api api;
    public static synchronized Api api() {
        if (api == null) {
            api = getRetrofit().create(Api.class);
        }
        return api;
    }

    private static MovieApi movieApi;
    public static synchronized MovieApi movieApi() {
        if (movieApi == null) {
            movieApi = getRetrofit().newBuilder()
                    .baseUrl(MovieApi.BASE_URL)
                    .build()
                    .create(MovieApi.class);
        }
        return movieApi;
    }
}
