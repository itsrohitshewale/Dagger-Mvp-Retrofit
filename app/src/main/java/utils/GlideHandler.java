package utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Singleton;

import in.nextgendevelopers.daggerapp.R;

@Singleton
public class GlideHandler {

    private RequestManager glide;

    public GlideHandler() {
    }

    public GlideHandler(Context context) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        glide = Glide.with(context)
                .setDefaultRequestOptions(requestOptions);
    }


    public RequestManager getGlide() {
        return glide;
    }
}
