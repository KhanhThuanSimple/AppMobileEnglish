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

    private final Context context;
    private final List<LearningOption> optionList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(LearningOption option);
    }

    public LearningOptionAdapter(Context context, List<LearningOption> optionList, OnItemClickListener listener) {
        this.context = context;
        this.optionList = optionList;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningOption option = optionList.get(position);
        holder.bind(option, listener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_learning_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(LearningOption option, OnItemClickListener listener) {
            tvTitle.setText(option.getTitle());
            imgIcon.setImageResource(option.getIconResId());

            itemView.setOnClickListener(v -> listener.onItemClick(option));
        }
    }
}

