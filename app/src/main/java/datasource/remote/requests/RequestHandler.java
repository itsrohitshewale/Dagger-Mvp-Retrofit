package datasource.remote.requests;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import android.util.Log;

import datasource.remote.respnse.GeneralResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.NetworkListener;

public class RequestHandler {

    private static final String TAG = "RequestHandler";

    public static <T> void execute(Call<T> call, NetworkListener networkListener, Context context) {
        execute(TAG, call, networkListener, context);
    }

    private static <T> void execute(final String TAG, Call<T> call, final NetworkListener networkListener, Context context) {
        //check if there is internet connection or not.
        if (checkInternetConnection(context)) {

            // if there is internet connection
            Log.d(TAG, call.request().toString());
            // Fire the interface implementation and get the server response
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(@NonNull Call<T> call, @NonNull retrofit2.Response<T> response) {
                    //If the request success! return the response body to the Activity.
                    if (response.errorBody() == null && response.body() != null){
                        networkListener.onResponse(TAG, response.body());
                    } else {
                        networkListener.onErrorResponse(TAG, new GeneralResponse("401", "Invalid API key"));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                    //If the request Failed! return the error response to the Activity.
                    networkListener.onErrorResponse(TAG, new GeneralResponse("404", "Error"));
                }
            });

        } else {
            // if there is no internet connection call noInternetConnection in the Activity to notify the user.
            networkListener.noInternetConnection();
        }
    }

    /**
     * This method take the application context and check on the internet connectivity.
     * if the device connected with the internet! the method will return true, else it will return false.
     * @param context  Activity context.
     * @return boolean true if internet connected, false if it not connected.
     * */
    private static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivityManager =

                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * To work with Retrofit you need basically three classes:
     * 1- Model class which is used to map the JSON data to
     * 2- Interfaces which defines the possible HTTP operations
     * 3- Retrofit.Builder class - Instance which uses the interface and the Builder API which allows
     * defining the URL end point for the HTTP operation.
     * */
    public static Retrofit getClient(String baseUrl) {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create()).build();

                // NOTE:: this retrofit missing the create() method!
    }
}
