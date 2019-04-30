package di.modules;

import android.app.Application;
import androidx.room.Room;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import datasource.MainActivityDataSource;
import datasource.remote.PhotosRemoteDataSource;
import di.scopes.Remote;
import utils.AppExecutors;
import utils.DiskIOThreadExecutor;

@Module
public abstract class MainActivityRepositoryModule {
    private static final int THREAD_COUNT = 3;

    @Singleton
    @Binds
    @Remote
    abstract MainActivityDataSource provideRemoteDataSource(PhotosRemoteDataSource dataSource);

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }
}
