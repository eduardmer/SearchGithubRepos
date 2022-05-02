package com.android_paging.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.android_paging.R;
import com.android_paging.databinding.LoadingStateLayoutBinding;

public class LoadingStateAdapter extends LoadStateAdapter<LoadingStateAdapter.MyViewHolder> {

    final ErrorState listener;

    public LoadingStateAdapter(ErrorState listener){
        this.listener=listener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        LoadingStateLayoutBinding binding;

        public MyViewHolder(@NonNull LoadingStateLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(LoadState loadState, ErrorState listener){
            binding.setVariable(BR.state, loadState instanceof LoadState.Loading);
            if (loadState instanceof LoadState.Error)
                binding.errorMsg.setText(((LoadState.Error) loadState).getError().getLocalizedMessage());
            binding.retryButton.setOnClickListener(v -> listener.retry());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        LoadingStateLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.loading_state_layout, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @NonNull LoadState loadState) {
        holder.bind(loadState, listener);
    }

    interface ErrorState{
        void retry();
    }

}
