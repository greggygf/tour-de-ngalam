package com.bluohazard.tourdengalam.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Playground;
import com.bumptech.glide.Glide;

public class PlaygroundViewHolder extends RecyclerView.ViewHolder {
    public TextView tvId, tvName;
    public ImageView image;

    public PlaygroundViewHolder(@NonNull View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tv_id);
        tvName = itemView.findViewById(R.id.tv_name_playground);
        image = itemView.findViewById(R.id.tv_image_playground);
    }

    public void bindToPlayground(Playground playground, View.OnClickListener onClickListener)
    {
        tvId.setText(String.valueOf(playground.id));
        tvName.setText(playground.name);
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }
}
