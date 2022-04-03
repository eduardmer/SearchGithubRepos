package com.android_paging.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android_paging.R;
import com.android_paging.data_model.RepositoryItems;
import com.android_paging.databinding.RepositoryItemLayoutBinding;

public class ReposAdapter extends PagingDataAdapter<RepositoryItems, ReposAdapter.MyViewHolder> {

    public ReposAdapter(@NonNull DiffUtil.ItemCallback<RepositoryItems> diffCallback) {
        super(diffCallback);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        RepositoryItemLayoutBinding binding;
        public MyViewHolder(@NonNull RepositoryItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(RepositoryItems data){
            binding.setVariable(BR.data, data);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public ReposAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RepositoryItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.repository_item_layout, parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.MyViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
