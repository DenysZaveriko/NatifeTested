package com.example.natifetested.recycleservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.natifetested.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context ctx;
    ArrayList<DataModel> modelList;

    private OnItemClickListener onItemClickListener;

    public DataAdapter(Context ctx, ArrayList<DataModel> modelList) {
        this.ctx = ctx;
        this.modelList = modelList;
    }
    public interface OnItemClickListener{
        void OnItemClick (int pos);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemgiphy,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel data = modelList.get(position);

        Glide.with(ctx).load(data.getImageURL()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            imageView = itemView.findViewById(R.id.imageGifView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            onItemClickListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
