package di.modules;

import dagger.Binds;
import dagger.Module;
import di.scopes.ActivityScope;
import in.nextgendevelopers.daggerapp.mainscreen.MainActivityContract;
import in.nextgendevelopers.daggerapp.mainscreen.MainActivityPresenter;

@Module
public abstract class MainActivityModule {

    @ActivityScope
    @Binds
    abstract MainActivityContract.Presenter mainActivityPresenter(MainActivityPresenter presenter);

}
