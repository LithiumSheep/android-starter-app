package com.lithium.apitutorial.network;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetworkCallback<T> implements Callback<T> {

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {

    }
}
