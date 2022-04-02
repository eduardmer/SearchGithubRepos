package com.android_paging.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.android_paging.BR;
import com.android_paging.R;
import com.android_paging.databinding.LoadingStateLayoutBinding;

public class LoadingStateAdapter extends LoadStateAdapter<LoadingStateAdapter.MyViewHolder> {

    static class MyViewHolder extends RecyclerView.ViewHolder{

        LoadingStateLayoutBinding binding;

        public MyViewHolder(@NonNull LoadingStateLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(LoadState loadState){
            binding.setVariable(BR.state, loadState instanceof LoadState.Loading);
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
        holder.bind(loadState);
    }

}
