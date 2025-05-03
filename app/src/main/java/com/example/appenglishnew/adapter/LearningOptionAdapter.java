package com.example.appenglishnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglishnew.R;
import com.example.appenglishnew.model.LearningOption;

import java.util.List;

public class LearningOptionAdapter extends RecyclerView.Adapter<LearningOptionAdapter.ViewHolder> {

    private final List<LearningOption> options;
    private final Context context;

    public LearningOptionAdapter(Context context, List<LearningOption> options) {
        this.context = context;
        this.options = options;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_learning_option, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningOption option = options.get(position);
        holder.tvTitle.setText(option.getTitle());
        holder.imgIcon.setImageResource(option.getIconResId());
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
