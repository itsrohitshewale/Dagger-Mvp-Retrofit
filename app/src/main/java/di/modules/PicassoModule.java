package di.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import utils.PicassoHandler;

@Module
public abstract class PicassoModule {

    @Provides
    static PicassoHandler providePicassoHandler(Application context) {
        return new PicassoHandler(context);
    }

}
