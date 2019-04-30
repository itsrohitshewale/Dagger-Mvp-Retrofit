package datasource;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import model.Photo;
import services.NetworkListener;

public interface MainActivityDataSource {

    interface LoadPhotosCallback extends NetworkListener {
        void onPhotosLoaded(ArrayList<Photo> photos);
        void onPhotosNotAvailable();
    }


//    default void refreshData(){}
    void getPhotos(@NonNull LoadPhotosCallback callback);

    default void savePhotos(ArrayList<Photo> photos){};
//    default void saveMostPopularMovies(ArrayList<Movie> movies){}
//    default void saveMovieTrailers(ArrayList<MovieTrailers> movieTrailers, int movieId){}
//    default void saveMovieReviews(ArrayList<MovieReviews> movieReviews, int movieId){}

    default void updatePhotos(Photo photo){};

    default void deleteAllPhotos(){};

}
