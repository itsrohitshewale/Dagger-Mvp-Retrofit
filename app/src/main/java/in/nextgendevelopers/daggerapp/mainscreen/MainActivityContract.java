package in.nextgendevelopers.daggerapp.mainscreen;

import android.app.Activity;

import java.util.ArrayList;

import model.Photo;
import services.BasePresenter;

public interface MainActivityContract {
    interface View{
        void showPhotos(ArrayList<Photo> photos, boolean setAdapter);
        void showNoPhotos();
        void showServerError(String error);
        void showNoInternetConnection();
    }

    interface Presenter extends BasePresenter<View> {
        void loadPhotos();
        void setBasicInit(boolean setAdapter);
        void setActivity(Activity activity);
    }
}
