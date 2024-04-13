package com.example.hospitalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReactionAdapter extends RecyclerView.Adapter<ReactionAdapter.ViewHolder> {
    private List<Reaction> reactionList;
    public ReactionAdapter(List<Reaction> reactionList) {
        this.reactionList = reactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reaction reaction = reactionList.get(position);
        if (reaction != null) {
            holder.textCode.setText("Code: " + reaction.getCode());
            if (reaction.getRating()==0)
            {
                holder.textRating.setText("Rating: no rating");
            }
            else
                holder.textRating.setText("Rating: " + reaction.getRating());
            holder.textDate.setText("Date: " + reaction.getDate());
            holder.textTime.setText("Time: " + reaction.getTime());
            holder.textDescription.setText("Description: " + reaction.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return reactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textCode,textRating,textDate,textTime,textDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCode = itemView.findViewById(R.id.textCode);
            textRating = itemView.findViewById(R.id.textRating);
            textDate = itemView.findViewById(R.id.textDate);
            textTime = itemView.findViewById(R.id.textTime);
            textDescription = itemView.findViewById(R.id.textDescription);
        }
    }
}
