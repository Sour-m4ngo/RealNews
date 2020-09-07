package com.example.realnews.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.realnews.Bean.Detail;
import com.example.realnews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<Detail> DataList;
    public NewsAdapter(ArrayList<Detail> data) {
        DataList = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//用于创建ViewHolder实例
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//用于对RecyclerView子项的数据进行复制，在子项被滚动到屏幕内的时候调用
        Detail AdapterData = DataList.get(position);

        holder.tvNewsTitle.setText(AdapterData.getTitle());
        holder.tvNewsTime.setText("发布时间： "+AdapterData.getTime());
        holder.tvSource.setText("来源：  "+AdapterData.getSrc());

        //Glide.with(view).load(AdapterData.getUrl()).into(holder.ivPicture);
        //loadImage(requestManager,AdapterData.getUrl(),holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvNewsTitle;
        @BindView(R.id.tv_time)
        TextView tvNewsTime;
        @BindView(R.id.tv_source)
        TextView tvSource;

//        @BindView(R.id.iv_pic)
//        ImageView ivPicture;
//        TextView tvNewsTitle;
//        TextView tvNewsTime;
//        TextView tvSource;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
//            tvNewsTitle = itemView.findViewById(R.id.tv_title);
//            tvNewsTime = itemView.findViewById(R.id.tv_time);
//            tvSource = itemView.findViewById(R.id.tv_source);
        }
    }
     void loadImage(RequestManager glide, String url, ImageView view) {
        glide.load(url).into(view);
    }
    public void ChangeAllData(ArrayList<Detail> dataList){
        this.DataList = dataList;
        notifyDataSetChanged();
    }
}
