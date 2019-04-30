package services;

import javax.inject.Inject;

import androidx.annotation.VisibleForTesting;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import di.DaggerAppComponent;
import repositories.MainActivityRepository;

public class AppController extends DaggerApplication {

    @Inject
    MainActivityRepository mainRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @VisibleForTesting
    public MainActivityRepository getMainRepository() {
        return mainRepository;
    }

}
