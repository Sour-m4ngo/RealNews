package com.example.realnews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.realnews.Bean.DataBaseBean.CityBaseInfoBean;
import com.example.realnews.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityWeatherInfoAdapter extends RecyclerView.Adapter<CityWeatherInfoAdapter.ViewHolder> {
    private ArrayList<CityBaseInfoBean> CityWeatherList ;

    public CityWeatherInfoAdapter(ArrayList<CityBaseInfoBean> List) {
        CityWeatherList = List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.DistrivtName.setText(CityWeatherList.get(position).getDistrictName());
        holder.CityName.setText(CityWeatherList.get(position).getCityName());
    }

    @Override
    public int getItemCount() {
        return CityWeatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_district_name)
        TextView DistrivtName;
        @BindView(R.id.tv_city_name_adm2)
        TextView CityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
