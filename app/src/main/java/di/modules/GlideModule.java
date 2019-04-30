package di.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import utils.GlideHandler;

@Module
public abstract class GlideModule {
    @Provides
    static GlideHandler provideGlideHandler(Application context) {
        return new GlideHandler(context);
    }
}
