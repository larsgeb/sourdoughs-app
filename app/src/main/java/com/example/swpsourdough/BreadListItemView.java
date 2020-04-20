package com.example.swpsourdough;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BreadListItemView
        extends RecyclerView.Adapter<BreadListItemView.ViewHolder> {

    private List<BreadListItem> mListOfBreads;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    BreadListItemView(Context context, List<BreadListItem> mListOfBreads) {
        this.mInflater = LayoutInflater.from(context);
        this.mListOfBreads = mListOfBreads;
    }

    // inflates the row layout from xml when needed
    @NonNull @Override public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = mInflater.inflate(R.layout.bread_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(
            ViewHolder holder, int position) {
        BreadListItem breadListItem = mListOfBreads.get(position);

        holder.textViewBaker.setText(breadListItem.mBaker);
        holder.textViewID.setText("#" + breadListItem.mID);
        holder.textViewFlour.setText(breadListItem.mFlour);

        holder.fluffBar.setProgress(breadListItem.mFluff * 10);
        holder.prettyBar.setProgress(breadListItem.mPretty * 10);
        holder.crunchBar.setProgress(breadListItem.mCrunch * 10);
        holder.tasteBar.setProgress(breadListItem.mTaste * 10);

        holder.hydrationBar.setProgress(breadListItem.mHydration);


    }

    // total number of rows
    @Override public int getItemCount() {
        return mListOfBreads.size();
    }

    // convenience method for getting data at click position
    BreadListItem getItem(int id) {
        return mListOfBreads.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView textViewID, textViewBaker, textViewFlour;
        ProgressBar fluffBar, prettyBar, crunchBar, hydrationBar, tasteBar;

        ViewHolder(View BreadListView) {
            super(BreadListView);
            textViewID = BreadListView.findViewById(R.id.breadID);
            textViewBaker = BreadListView.findViewById(R.id.baker);
            textViewFlour = BreadListView.findViewById(R.id.flour);
            fluffBar = BreadListView.findViewById(R.id.fluffBar);
            prettyBar = BreadListView.findViewById(R.id.prettyBar);
            crunchBar = BreadListView.findViewById(R.id.crunchBar);
            hydrationBar = BreadListView.findViewById(R.id.hydrationBar);
            tasteBar = BreadListView.findViewById(R.id.tasteBar);

            BreadListView.setOnClickListener(this);
        }

        @Override public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}