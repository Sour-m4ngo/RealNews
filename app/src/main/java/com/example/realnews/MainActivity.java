package com.example.realnews;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.realnews.BaseMVP.BaseActivity;
import com.example.realnews.NewsMVP.NewsFragment;
import com.example.realnews.NoteMVP.NoteFragment;
import com.example.realnews.Util.InjectPresenter;
import com.example.realnews.NewsMVP.NewsApplyPresenter;
import com.example.realnews.WeatherMVP.WeatherFragment;
import com.example.realnews.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 *
 */
public class MainActivity extends BaseActivity implements MainContract.IMainViewActivity {

    @BindView(R.id.main_view_pager)
    ViewPager mviewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private ArrayList<Fragment> mFragmentList;

    @InjectPresenter
    private NewsApplyPresenter mPresenter;
    private NewsFragment newsFragment = new NewsFragment();
    private WeatherFragment weatherFragment;
    private NoteFragment noteFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private View getView(int i) {
        View view = View.inflate(this, R.layout.tab_item, null);
        ViewHolder holder = new ViewHolder(view);
        switch (i) {
            case 0:
                holder.getTabItemIcon().setImageResource(R.drawable.icon_note);
                holder.getTabItemName().setText("便签");
                break;
            case 1:
                holder.getTabItemIcon().setImageResource(R.drawable.icon_news);
                holder.getTabItemName().setText("新闻");
                break;
            case 2:
                holder.getTabItemIcon().setImageResource(R.drawable.icon_weather);
                holder.getTabItemName().setText("天气");
                break;
        }
        return view;
    }



    static
    class ViewHolder {
        @BindView(R.id.tab_item_icon)
        ImageView tabItemIcon;
        @BindView(R.id.tab_item_name)
        TextView tabItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public ImageView getTabItemIcon() {
            return tabItemIcon;
        }

        public TextView getTabItemName() {
            return tabItemName;
        }
    }

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new NoteFragment());
        mFragmentList.add(newsFragment);
        mFragmentList.add(new WeatherFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), 1, mFragmentList);
        mviewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mviewPager);
        for (int i = 0; i < fragmentAdapter.getCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(getView(i));
        }

    }
}
