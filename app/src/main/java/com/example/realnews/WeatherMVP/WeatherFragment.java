package com.example.realnews.WeatherMVP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.realnews.BaseMVP.BaseFragment;
import com.example.realnews.Bean.Detail;
import com.example.realnews.Bean.Now;
import com.example.realnews.MainContract;
import com.example.realnews.R;
import com.example.realnews.Util.InjectPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class WeatherFragment extends BaseFragment implements MainContract.IViewWeatherFragment{
    @BindView(R.id.btn_select_city)
    Button btnSelectCity;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_weather_like)
    TextView tvWeatherLike;
    @BindView(R.id.tv_feelsLike)
    TextView tvFeelsLike;
    @BindView(R.id.tv_wind_dir_scale)
    TextView tvWindDirScale;
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.ima_test)
    ImageView imaTest;
    @BindView(R.id.tv_test)
    TextView tvTest;
    @InjectPresenter
    private WeatherApplyPressenter mPresenter;

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mPresenter = new WeatherApplyPressenter();
        mPresenter.attach(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.HandleData();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_weather;
    }

    @OnClick(R.id.btn_select_city)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(),CityListActivity.class);
        startActivity(intent);
    }


    @Override
    public void SetWeatherData(Now now) {
         tvCityName.setText("beijing");
         tvTemperature.setText(now.getTemp());
        tvWeatherLike.setText(now.getText());
        tvFeelsLike.setText(now.getFeelsLike());
        tvWindDirScale.setText(now.getWindDir()+" : "+now.getWindScale());
    }

    @Override
    public void show(String s) {
        tvTemperature.setText(s);
    }
}
