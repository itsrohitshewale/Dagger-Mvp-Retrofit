package repositories;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import datasource.MainActivityDataSource;
import datasource.remote.respnse.GeneralResponse;
import di.scopes.Local;
import di.scopes.Remote;
import model.Photo;

@Singleton
public class MainActivityRepository implements MainActivityDataSource {

    private final MainActivityDataSource mMoviesRemoteDataSource;

    @Inject
    MainActivityRepository(@Remote MainActivityDataSource moviesRemoteDataSource) {
        mMoviesRemoteDataSource = moviesRemoteDataSource;
    }

    private void getPhotosFromServer(@NonNull LoadPhotosCallback callback) {
        mMoviesRemoteDataSource.getPhotos(callback);
    }

    @Override
    public void getPhotos(@NonNull final LoadPhotosCallback callback) {
        getPhotosFromServer(callback);
    }
}
