package com.bluohazard.tourdengalam.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Beach;
import com.bumptech.glide.Glide;

public class BeachViewHolder extends RecyclerView.ViewHolder {
    private AdapterView.OnItemClickListener listener;

    public TextView tvName, tvLocationTitle;
    public ImageView image;
    public CardView cv;

    public BeachViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tv_name_beach);
        tvLocationTitle = itemView.findViewById(R.id.tv_location_beach);
        image = itemView.findViewById(R.id.tv_image_beach);
        cv = itemView.findViewById(R.id.cardView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void bindToBeach(Beach beach, View.OnClickListener onClickListener)
    {
        tvName.setText(beach.name);
        tvLocationTitle.setText(beach.location_title);
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }
}
