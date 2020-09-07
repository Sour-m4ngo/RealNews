package com.example.realnews.WeatherMVP;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.realnews.Bean.CityWeatherInfo;
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
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityListActivity extends AppCompatActivity {

    private CityWeatherInfoAdapter cityWeatherInfoAdapter;
    private ArrayList<CityWeatherInfo> mCityWeatherInfoList;
    private LinearLayoutManager LayoutManager;
    @BindView(R.id.recycler_view)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.btn_add_city)
    FloatingActionButton btnAddCity;
    @BindView(R.id.test)
    TextView test;
    @InjectPresenter
    private CityApplyPressenter cityApplyPressenter;
    @InjectPresenter
    private WeatherApplyPressenter weatherApplyPressenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list_activity);
        ButterKnife.bind(this);
        initData();
        initAdapter();
    }

    void initData() {

    }

    private void initAdapter(){
        ArrayList<CityWeatherInfo> BlankDataList = new ArrayList<>();
        for (int a = 0;a < 5;a++){
            CityWeatherInfo cityWeatherInfo = new CityWeatherInfo("test","test");
            BlankDataList.add(cityWeatherInfo);
        }
        mCityWeatherInfoList = BlankDataList;
        cityWeatherInfoAdapter = new CityWeatherInfoAdapter(mCityWeatherInfoList);
        LayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(LayoutManager);
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
                cityApplyPressenter.searchCity(city.getName());

            }

            @Override
            public void onCancel() {
            }
        });
        cityPicker.showCityPicker();
    }
}
