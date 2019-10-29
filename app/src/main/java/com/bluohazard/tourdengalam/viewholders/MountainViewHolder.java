package com.bluohazard.tourdengalam.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Mountain;

public class MountainViewHolder extends RecyclerView.ViewHolder {
    public TextView tvId, tvName;

    public MountainViewHolder(@NonNull View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tv_id);
        tvName = itemView.findViewById(R.id.tv_name_mountain);
    }

    public void bindToMountain(Mountain mountain, View.OnClickListener onClickListener)
    {
        tvId.setText(String.valueOf(mountain.id));
        tvName.setText(mountain.name);
    }
}
