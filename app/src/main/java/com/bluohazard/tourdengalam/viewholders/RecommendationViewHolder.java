package com.bluohazard.tourdengalam.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Recommendation;
import com.bumptech.glide.Glide;

public class RecommendationViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName, tvLocationTitle;
    public ImageView image;
    public CardView cv;

    public RecommendationViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tv_name_recommendation);
        tvLocationTitle = itemView.findViewById(R.id.tv_location_recommendation);
        image = itemView.findViewById(R.id.tv_image_recommendation);
        cv = itemView.findViewById(R.id.cardViewRecommendation);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void bindToRecommendation(Recommendation recommendation, View.OnClickListener onClickListener) {
        tvName.setText(recommendation.name);
        tvLocationTitle.setText(recommendation.location_title);
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }
}
