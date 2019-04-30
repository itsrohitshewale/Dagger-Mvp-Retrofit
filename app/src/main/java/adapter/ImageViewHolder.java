package adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import in.nextgendevelopers.daggerapp.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title;
    ImageView image;
    OnCellClickListener onCellClickListener;

    public ImageViewHolder(@NonNull View itemView, OnCellClickListener onCellClickListener) {
        super(itemView);

        this.onCellClickListener = onCellClickListener;

        title = itemView.findViewById(R.id.txtTitle);
        image = itemView.findViewById(R.id.imgview_thumbnail);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onCellClickListener.onCellClick(getAdapterPosition());
    }
}





