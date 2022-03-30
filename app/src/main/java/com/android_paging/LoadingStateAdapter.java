package com.android_paging;

import android.media.tv.TvContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class LoadingStateAdapter extends LoadStateAdapter<LoadingStateAdapter.MyViewHolder> {

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ProgressBar progressBar;
        TextView text;
        Button button;

        public MyViewHolder(@NonNull View view) {
            super(view);
            progressBar=view.findViewById(R.id.progress_bar);
            text=view.findViewById(R.id.error_msg);
            button=view.findViewById(R.id.retry_button);
        }

        public void bind(LoadState loadState){
            if (loadState instanceof LoadState.Loading){
                text.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @NonNull LoadState loadState) {
        holder.bind(loadState);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_state, viewGroup, false);
        return new MyViewHolder(view);
    }
}
