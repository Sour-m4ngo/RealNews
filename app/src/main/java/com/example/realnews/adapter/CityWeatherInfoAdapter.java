package com.example.realnews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realnews.Bean.CityWeatherInfo;
import com.example.realnews.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityWeatherInfoAdapter extends RecyclerView.Adapter<CityWeatherInfoAdapter.ViewHolder> {
    private ArrayList<CityWeatherInfo> CityWeatherList;

    public CityWeatherInfoAdapter(ArrayList<CityWeatherInfo> cityWeatherList) {
        CityWeatherList = cityWeatherList;
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
            holder.CityName.setText("test");
            holder.CurrentWeather.setText("test");
            holder.imaWeatherIcon.setBackgroundResource(R.drawable.add);
    }

    @Override
    public int getItemCount() {
        return CityWeatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city_name_adapter)
        TextView CityName;
        @BindView(R.id.tv_current_weather)
        TextView CurrentWeather;
        @BindView(R.id.ima_weather_icon)
        ImageView imaWeatherIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
