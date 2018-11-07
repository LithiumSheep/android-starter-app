package com.lithium.apitutorial.network;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Keep
public abstract class NetworkCallback<T> implements Callback<T> {

    protected abstract void onSuccess(T response);

    protected abstract void onFailure(Throwable t);

    @Override
    public final void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            this.onSuccess(response.body());
        } else {
            try {
                this.onFailure(new Throwable(response.errorBody().string()));
            } catch (IOException e) {
                this.onFailure(e);
            }
        }
    }

    @Override
    public final void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if (t instanceof UnknownHostException) {    // since we control the Service, this exception usually means no network
            onFailure(new Throwable("No network available, please check your WiFi or Data connection."));
        } else {
            onFailure(t);
        }
    }

}
