package com.example.realnews.WeatherMVP;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.realnews.BaseMVP.BaseFragment;
import com.example.realnews.Bean.ApiBean.Daily;
import com.example.realnews.Bean.ApiBean.Now;
import com.example.realnews.MainContract;
import com.example.realnews.R;
import com.example.realnews.Util.InjectPresenter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import butterknife.BindView;
import butterknife.OnClick;


public class WeatherFragment extends BaseFragment implements MainContract.IViewWeatherView {
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
    @BindView(R.id.day_in_week01)
    TextView DayForWeek01;
    @BindView(R.id.day_in_week02)
    TextView DayForWeek02;
    @BindView(R.id.day_in_week03)
    TextView DayForWeek03;
    @BindView(R.id.day_in_week04)
    TextView DayForWeek04;
    @BindView(R.id.day_in_week05)
    TextView DayForWeek05;
    @BindView(R.id.day_in_week06)
    TextView DayForWeek06;
    @BindView(R.id.day_in_week07)
    TextView DayForWeek07;
    @BindView(R.id.date01)
    TextView date01;
    @BindView(R.id.date02)
    TextView date02;
    @BindView(R.id.date03)
    TextView date03;
    @BindView(R.id.date04)
    TextView date04;
    @BindView(R.id.date05)
    TextView date05;
    @BindView(R.id.date06)
    TextView date06;
    @BindView(R.id.date07)
    TextView date07;
    @BindView(R.id.weather_icon01)
    ImageView weatherIcon01;
    @BindView(R.id.weather_icon02)
    ImageView weatherIcon02;
    @BindView(R.id.weather_icon03)
    ImageView weatherIcon03;
    @BindView(R.id.weather_icon04)
    ImageView weatherIcon04;
    @BindView(R.id.weather_icon05)
    ImageView weatherIcon05;
    @BindView(R.id.weather_icon06)
    ImageView weatherIcon06;
    @BindView(R.id.weather_icon07)
    ImageView weatherIcon07;
    @BindView(R.id.current_max_temperature01)
    TextView currentMaxTemperature01;
    @BindView(R.id.current_max_temperature02)
    TextView currentMaxTemperature02;
    @BindView(R.id.current_max_temperature03)
    TextView currentMaxTemperature03;
    @BindView(R.id.current_max_temperature04)
    TextView currentMaxTemperature04;
    @BindView(R.id.current_max_temperature05)
    TextView currentMaxTemperature05;
    @BindView(R.id.current_max_temperature06)
    TextView currentMaxTemperature06;
    @BindView(R.id.current_max_temperature07)
    TextView currentMaxTemperature07;
    @BindView(R.id.current_min_temperature01)
    TextView currentMinTemperature01;
    @BindView(R.id.current_min_temperature02)
    TextView currentMinTemperature02;
    @BindView(R.id.current_min_temperature03)
    TextView currentMinTemperature03;
    @BindView(R.id.current_min_temperature04)
    TextView currentMinTemperature04;
    @BindView(R.id.current_min_temperature05)
    TextView currentMinTemperature05;
    @BindView(R.id.current_min_temperature06)
    TextView currentMinTemperature06;
    @BindView(R.id.current_min_temperature07)
    TextView currentMinTemperature07;
    @BindView(R.id.weather_like01)
    TextView weatherLike01;
    @BindView(R.id.weather_like02)
    TextView weatherLike02;
    @BindView(R.id.weather_like03)
    TextView weatherLike03;
    @BindView(R.id.weather_like04)
    TextView weatherLike04;
    @BindView(R.id.weather_like05)
    TextView weatherLike05;
    @BindView(R.id.weather_like06)
    TextView weatherLike06;
    @BindView(R.id.weather_like07)
    TextView weatherLike07;
    @InjectPresenter
    private NowWeatherPressenter mNowWeatherPresenter;
    @InjectPresenter
    private DailyWeatherPresenter mDailyWeatherPresenter;
    private String CityName = "北京";
    private String CityId = "101010100";

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
    protected int setLayout() {
        return R.layout.fragment_weather;
    }
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mNowWeatherPresenter = new NowWeatherPressenter();
        mDailyWeatherPresenter = new DailyWeatherPresenter();
        mNowWeatherPresenter.attach(this);
        mDailyWeatherPresenter.attach(this);
        SharedPreferences pref = getContext().getSharedPreferences("DefaultCityConfig", 0);
        if (IsFirstStart() == true) {
            //Toast.makeText(getContext(), "aa", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirstStart", false);
            editor.putString("defaultCityName", "北京");
            editor.putString("defaultCityId", "101010100");
            editor.commit();
        } else {
            CityName = pref.getString("defaultCityName", "北京");
            CityId = pref.getString("defaultCityId", "101010100");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mNowWeatherPresenter.GetWeather(CityId);
        mDailyWeatherPresenter.GetDailyWeather(CityId);
    }


    @OnClick({R.id.btn_select_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_select_city:
                Intent intent = new Intent(getContext(), CityListActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }


    @Override
    public void SetNowWeatherData(Now now) {
        tvCityName.setText(CityName);
        tvTemperature.setText(now.getTemp() + "℃");
        tvWeatherLike.setText(now.getText());
        tvFeelsLike.setText("体感温度：" + now.getFeelsLike() + "℃");
        tvWindDirScale.setText("风向：" + now.getWindDir() + "\n风速 : " + now.getWindScale() + "级");
    }

    @Override
    public void SetDailyWeatherData(ArrayList<Daily> dailyList) {
        SetListContent(dailyList);
        //Toast.makeText(getContext(),"123"+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCityId(String Id) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //这里要通过请求码来判断数据的来源
        switch (requestCode) {
            case 1:
                //判断请求的结果是否成功，resultCode == RESULT_OK，代表成功了
                if (resultCode == 1) {
                    CityId = data.getStringExtra("LocationId");
                    CityName = data.getStringExtra("CityName");
                    SharedPreferences pref = getContext().getSharedPreferences("DefaultCityConfig", 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("defaultCityName", CityName);
                    editor.putString("defaultCityId", CityId);
                    editor.commit();
                    mNowWeatherPresenter.GetWeather(CityId);
                    //Toast.makeText(getContext(), getData, Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private Boolean IsFirstStart() {
        Boolean isFirstStart = false;
        SharedPreferences preferences = getContext().getSharedPreferences("DefaultCityConfig", 0);
        isFirstStart = preferences.getBoolean("isFirstStart", true);
        return isFirstStart;

    }

    private void SetListContent(ArrayList<Daily> list) {
        TextView[] MaxTemperatureList = new TextView[]{currentMaxTemperature01, currentMaxTemperature02, currentMaxTemperature03, currentMaxTemperature04, currentMaxTemperature05, currentMaxTemperature06, currentMaxTemperature07};
        TextView[] MinTemperatureList = new TextView[]{currentMinTemperature01, currentMinTemperature02, currentMinTemperature03, currentMinTemperature04, currentMinTemperature05, currentMinTemperature06, currentMinTemperature07};
        ImageView[] weatherIconList = new ImageView[]{weatherIcon01, weatherIcon02, weatherIcon03, weatherIcon04, weatherIcon05, weatherIcon06, weatherIcon07};
        TextView[] DateList = new TextView[]{date01, date02, date03, date04, date05, date06, date07};
        TextView[] DayForWeekList = new TextView[]{DayForWeek01, DayForWeek02, DayForWeek03, DayForWeek04, DayForWeek05, DayForWeek06, DayForWeek07};
        TextView[] WeatherLikeList = new TextView[]{weatherLike01,weatherLike02,weatherLike03,weatherLike04,weatherLike05,weatherLike06,weatherLike07};

        for (int a = 0; a < list.size(); a++) {
            MaxTemperatureList[a].setText(list.get(a).getTempMax()+"℃");
            MinTemperatureList[a].setText(list.get(a).getTempMin()+"℃");
            DateList[a].setText(getDate(list.get(a).getFxDate()));
            DayForWeekList[a].setText(getWeekDay(list.get(a).getFxDate()));
            WeatherLikeList[a].setText(list.get(a).getTextDay());
            weatherIconList[a].setImageResource(String2ResID(list.get(a).getIconDay()));

        }

    }

    private String getWeekDay(Date date) {
        String Week = null;
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDate resDate = localDateTime1.toLocalDate();
        LocalDate nowDate = LocalDate.now();
        int WeekNow = resDate.getDayOfWeek().getValue();
        if (resDate.isEqual(nowDate)) {
            return "今天";
        } else {
            switch (WeekNow) {
                case 1:
                    Week = "周一";
                    break;
                case 2:
                    Week = "周二";
                    break;
                case 3:
                    Week = "周三";
                    break;
                case 4:
                    Week = "周四";
                    break;
                case 5:
                    Week = "周五";
                    break;
                case 6:
                    Week = "周六";
                    break;
                case 7:
                    Week = "周日";
                    break;
            }
        }
        return Week;

    }
    private String getDate(Date date) {
        StringBuilder resData = new StringBuilder(" ");
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDate resDate = localDateTime1.toLocalDate();

        resData.append(resDate.getMonthValue()).append("月").append(resDate.getDayOfMonth()).append("日");
        return resData.toString();
    }
    private int String2ResID(String IconId){
        StringBuilder resString = new StringBuilder("weather_icon");
        resString.append(IconId);
        int resId = getResources().getIdentifier(resString.toString(),"drawable",getActivity().getPackageName());
        return resId;
    }
}
