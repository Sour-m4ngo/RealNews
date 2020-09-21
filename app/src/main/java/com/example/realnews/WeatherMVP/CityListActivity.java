package com.example.realnews.WeatherMVP;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.realnews.BaseMVP.BaseActivity;
import com.example.realnews.Bean.ApiBean.Daily;
import com.example.realnews.Bean.DataBaseBean.CityBaseInfoBean;
import com.example.realnews.Bean.ApiBean.Now;
import com.example.realnews.MainContract;
import com.example.realnews.R;
import com.example.realnews.Util.InjectPresenter;
import com.example.realnews.adapter.CityWeatherInfoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class CityListActivity  extends BaseActivity implements MainContract.IViewWeatherView  {

    private CityWeatherInfoAdapter cityWeatherInfoAdapter;
    private ArrayList<CityBaseInfoBean> mCityWeatherInfoList;
    private LinearLayoutManager LayoutManager;
    private Intent intent;
    private CityBaseInfoBean CurrentCityData;
    private List<CityBaseInfoBean> tempList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.btn_add_city)
    FloatingActionButton btnAddCity;

    @InjectPresenter
    private CityApplyPressenter cityApplyPressenter;
    @InjectPresenter
    private NowWeatherPressenter nowWeatherPressenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list_activity);
        ButterKnife.bind(this);
        initData();
        initAdapter();
        SetData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetData();
    }

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {
        CurrentCityData = new CityBaseInfoBean();
        LitePal.getDatabase();
        tempList = LitePal.findAll(CityBaseInfoBean.class);
    }


    private void initAdapter(){
        mCityWeatherInfoList = new ArrayList<>();
        cityWeatherInfoAdapter = new CityWeatherInfoAdapter(mCityWeatherInfoList);
        LayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                intent = new Intent();
                intent.putExtra("LocationId",mCityWeatherInfoList.get(adapterPosition).getCityId());
                intent.putExtra("CityName",mCityWeatherInfoList.get(adapterPosition).getCityName());
                setResult(1,intent);
                finish();
                Log.d(TAG, " "+adapterPosition);
            }
        });
        recyclerView.setAdapter(cityWeatherInfoAdapter);
    }
    @OnClick(R.id.btn_add_city)
    public void onViewClicked() {
        JDCityPicker cityPicker = new JDCityPicker();
        JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
        jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
        cityPicker.init(this);
        cityPicker.setConfig(jdCityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

//                test.setText("城市选择结果：\n" + province.getName() + "(" + province.getId() + ")\n"
//                        + city.getName() + "(" + city.getId() + ")\n"
//                        + district.getName() + "(" + district.getId() + ")");
                CurrentCityData = new CityBaseInfoBean(city.getName(),district.getName());
                if (IsHaveSameData(district.getName(),city.getName())){
                    Toast.makeText(getContext(),"该地区已存在，请重新选择",Toast.LENGTH_SHORT).show();
                }else {
                mCityWeatherInfoList.add(CurrentCityData);
                cityWeatherInfoAdapter.notifyDataSetChanged();
                cityApplyPressenter.HandleData(district.getName());//将从选择器得到区域名作为参数传入p层获取cityId
                }
            }
            @Override
            public void onCancel() {
            }
        });
        cityPicker.showCityPicker();
    }

    @Override
    public void SetNowWeatherData(Now now) { }
    @Override
    public void SetDailyWeatherData(ArrayList<Daily> dailyList) { }


    @Override
    public void getCityId(String id) {//P层调用本方法将获取的cityID传入
     CurrentCityData.setCityId(id);
     CurrentCityData.save();
    }

    public void SetData(){
        mCityWeatherInfoList.addAll(tempList);
        cityWeatherInfoAdapter.notifyDataSetChanged();
    }

    private Boolean IsHaveSameData(String districtName,String CityName){
        List<CityBaseInfoBean> tempList = LitePal
                .where("CityName = ? and DistrictName = ?",CityName,districtName)
                .find(CityBaseInfoBean.class);
        if (tempList.size()==0){
            return false;
        }else {
            return true;
        }

    }
}
