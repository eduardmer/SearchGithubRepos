package com.android_paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android_paging.data_model.RepositoryItems;

public class ReposAdapter extends PagingDataAdapter<RepositoryItems, ReposAdapter.MyViewHolder> {

    public ReposAdapter(@NonNull DiffUtil.ItemCallback<RepositoryItems> diffCallback) {
        super(diffCallback);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.repo_name);
        }
    }


    @NonNull
    @Override
    public ReposAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.MyViewHolder holder, int position) {
        RepositoryItems item=getItem(position);
        holder.title.setText(item.getFullName());
    }
}
