package com.bluohazard.tourdengalam.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.models.Playground;

public class PlaygroundViewHolder extends RecyclerView.ViewHolder {
    public TextView tvId, tvName;

    public PlaygroundViewHolder(@NonNull View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tv_id);
        tvName = itemView.findViewById(R.id.tv_name_playground);
    }

    public void bindToPlayground(Playground playground, View.OnClickListener onClickListener)
    {
        tvId.setText(String.valueOf(playground.id));
        tvName.setText(playground.name);
    }
}
