package services;

import datasource.remote.respnse.GeneralResponse;

public interface NetworkListener {
    void noInternetConnection();
    void onResponse(String TAG, Object response);
    void onErrorResponse(String TAG, GeneralResponse response);
}
