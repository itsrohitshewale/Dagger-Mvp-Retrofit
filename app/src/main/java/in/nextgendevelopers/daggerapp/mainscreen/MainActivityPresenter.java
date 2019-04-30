package in.nextgendevelopers.daggerapp.mainscreen;

import android.app.Activity;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import datasource.MainActivityDataSource;
import datasource.remote.respnse.GeneralResponse;
import model.Photo;
import repositories.MainActivityRepository;

public class MainActivityPresenter implements MainActivityContract.Presenter{

    @Nullable
    private MainActivityContract.View view;
    private MainActivityRepository mainRepository;
    private boolean setAdapter = true;
//    private boolean forceUpdate;

    @Inject
    MainActivityPresenter(MainActivityRepository mainRepository) {
        this.mainRepository = mainRepository;
    }


    @Nullable
    private Activity activity;


    @Override
    public void loadPhotos() {
        startLoading();
    }

    private void startLoading() {

        mainRepository.getPhotos(new MainActivityDataSource.LoadPhotosCallback() {
            @Override
            public void noInternetConnection() {
                view.showNoInternetConnection();
                onPhotosNotAvailable();
            }

            @Override
            public void onResponse(String TAG, Object response) {

//                ArrayList<Photo> photos = ((PhotosResponse) response).getResults();
                view.showPhotos((ArrayList<Photo>) response, setAdapter);
//                mainRepository.deleteAllPhotos();
//                mainRepository.savePhotos(((PhotosResponse) response).getResults());
//                view.updateView();
//                if (mode == Constants.NORMAL_MODE) {
//                    startLoading(mode, false);
//                    startLoading(MoviesFilter.TOP_RATED, true, false);
//                } else if (mode == Constants.SCROLL_MORE_MODE) {
//                    moviesRepository.saveTopRatedMovies(((MoviesResponse) response).getResults());
//                    if (initialCall)
//                        startLoading(movieType, false, false);
//                }
            }

            @Override
            public void onErrorResponse(String TAG, GeneralResponse response) {
                view.showServerError(response.getMessage());
            }

            @Override
            public void onPhotosLoaded(ArrayList<Photo> photos) {
                if (photos.size() > 0) {
                    view.showPhotos(photos, setAdapter);
                } else {
                    view.showNoPhotos();
                }
            }

            @Override
            public void onPhotosNotAvailable() {
                view.showNoPhotos();
            }
        });

    }


    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void setBasicInit(boolean setAdapter) {
        this.setAdapter = setAdapter;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
