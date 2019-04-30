package adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.nextgendevelopers.daggerapp.R;
import model.Photo;
import utils.GlideHandler;

public class PhotosRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> mPhotos;
    private OnCellClickListener mOnCellClickListener;
    GlideHandler glideHandler;

    public PhotosRecyclerAdapter(ArrayList<Photo> list, OnCellClickListener mOnCellClickListener, GlideHandler handler) {
        this.mPhotos = list;
        this.mOnCellClickListener = mOnCellClickListener;
        this.glideHandler = handler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ImageViewHolder(view, mOnCellClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        glideHandler.getGlide()
                .load(mPhotos.get(i).getThumbnailUrl())
                .into(((ImageViewHolder)viewHolder).image);

        ((ImageViewHolder)viewHolder).title.setText(mPhotos.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        if(mPhotos != null){
            return mPhotos.size();
        }
        return 0;
    }

    public void setPhotos(List<Photo> photos){
        mPhotos = photos;
        notifyDataSetChanged();
    }
}
