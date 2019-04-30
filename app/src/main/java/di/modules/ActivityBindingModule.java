package di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import di.scopes.ActivityScope;
import in.nextgendevelopers.daggerapp.mainscreen.MainActivity;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();
}
