package com.bluohazard.tourdengalam.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Beach;

public class BeachViewHolder extends RecyclerView.ViewHolder {
    public TextView tvId, tvName;

    public BeachViewHolder(@NonNull View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tv_id);
        tvName = itemView.findViewById(R.id.tv_name_beach);
    }

    public void bindToBeach(Beach beach, View.OnClickListener onClickListener)
    {
        tvId.setText(beach.id);
        tvName.setText(beach.name);
    }
}
