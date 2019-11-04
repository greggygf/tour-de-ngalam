package com.bluohazard.tourdengalam.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Beach;
import com.bumptech.glide.Glide;

public class BeachViewHolder extends RecyclerView.ViewHolder {
    public TextView tvId, tvName;
    public ImageView image;

    public BeachViewHolder(@NonNull View itemView) {
        super(itemView);

        tvId = itemView.findViewById(R.id.tv_id);
        tvName = itemView.findViewById(R.id.tv_name_beach);
        image = itemView.findViewById(R.id.tv_image_beach);
    }

    public void bindToBeach(Beach beach, View.OnClickListener onClickListener)
    {
        tvId.setText(String.valueOf(beach.id));
        tvName.setText(beach.name);
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }
}
