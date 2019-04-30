package di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import di.modules.ActivityBindingModule;
import di.modules.GlideModule;
import di.modules.MainActivityRepositoryModule;
import di.modules.PicassoModule;
import services.AppController;

@Singleton
@Component(modules = {ApplicationModule.class,
        MainActivityRepositoryModule.class,
        PicassoModule.class,
        GlideModule.class,
        ActivityBindingModule.class,
        AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<AppController> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
