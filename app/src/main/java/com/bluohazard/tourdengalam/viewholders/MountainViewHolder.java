package com.bluohazard.tourdengalam.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Mountain;
import com.bumptech.glide.Glide;

public class MountainViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName, tvLocationTitle;
    public ImageView image;

    public MountainViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tv_name_mountain);
        tvLocationTitle = itemView.findViewById(R.id.tv_location_mountain);
        image = itemView.findViewById(R.id.tv_image_mountain);
    }

    public void bindToMountain(Mountain mountain, View.OnClickListener onClickListener) {
        tvName.setText(mountain.name);
        tvLocationTitle.setText(mountain.location_title);
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }
}
