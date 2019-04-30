package datasource.remote;

import android.app.Application;
import androidx.annotation.NonNull;


import javax.inject.Inject;
import javax.inject.Singleton;

import datasource.MainActivityDataSource;
import datasource.remote.requests.ApplicationApi;
import datasource.remote.requests.RequestHandler;
import model.Photo;
import utils.Constants;

@Singleton
public class PhotosRemoteDataSource implements MainActivityDataSource {
    private Application application;

    @Inject
    PhotosRemoteDataSource(Application application) {
        this.application = application;
    }

    @Override
    public void getPhotos(@NonNull LoadPhotosCallback callback) {
        ApplicationApi api = RequestHandler.getClient(Constants.BASE_URL).create(ApplicationApi.class);

        loadPhotos(api, callback);
//        switch (mode) {
//            case Constants.NORMAL_MODE:
//
//                break;
//            case Constants.SCROLL_MORE_MODE:
//                //load another set of api
//                break;
//            default:
//                callback.onPhotosNotAvailable();
//        }
    }

    private void loadPhotos(@NonNull ApplicationApi applicationApi, @NonNull LoadPhotosCallback callback) {
        // Execute the API and passing the callback which implemented in {@link MoviesRepository}.
        // By passing the call back to the execute method, when the server response return, callback
        // will be fired.

        RequestHandler.execute(
                applicationApi.getAllPhotos(),
                callback,
                application.getApplicationContext()
        );
    }

    @Override
    public void updatePhotos(Photo photo) {

    }

    @Override
    public void deleteAllPhotos() {

    }
}
