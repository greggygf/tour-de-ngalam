package com.bluohazard.tourdengalam.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Playground;
import com.bumptech.glide.Glide;

public class PlaygroundViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName, tvLocationTitle;
    public ImageView image;
    public CardView cv;

    public PlaygroundViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tv_name_playground);
        tvLocationTitle = itemView.findViewById(R.id.tv_location_playground);
        image = itemView.findViewById(R.id.tv_image_playground);
        cv = itemView.findViewById(R.id.cardViewPlayground);
    }

    public void bindToPlayground(Playground playground, View.OnClickListener onClickListener) {
        tvName.setText(playground.name);
        tvLocationTitle.setText(playground.location_title);
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }
}
