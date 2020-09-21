package com.example.realnews.NewsMVP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.realnews.BaseMVP.BaseFragment;
import com.example.realnews.Bean.ApiBean.Detail;
import com.example.realnews.Util.InjectPresenter;
import com.example.realnews.MainContract;
import com.example.realnews.R;
import com.example.realnews.adapter.NewsAdapter;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import java.util.ArrayList;
import butterknife.BindView;
import static android.content.ContentValues.TAG;


public class NewsFragment extends BaseFragment implements MainContract.IViewNewsFragment {
    @InjectPresenter
    private NewsApplyPresenter mPresenter;
    private LinearLayoutManager layoutManager;
    private NewsAdapter adapter;
    private ArrayList<Detail> mNewsDataList;
    /*
    @BindView(R.id.btn_start)
    Button BtnStart;
    @BindView(R.id.tv_test)
    TextView TvTest;
    @OnClick({R.id.btn_start})
    public void Toast(){
        Toast.makeText(getActivity(), "is a click", Toast.LENGTH_SHORT).show();

    }
    */
    @BindView(R.id.recycler_view)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_view)
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        ///////////////////////////////////////
    }

    @Override
    protected void initData() {
        mPresenter = new NewsApplyPresenter();
        mPresenter.attach(this);
        ArrayList<Detail> BlankDataList = new ArrayList<>();
        for (int a = 0;a < 5;a++){
            Detail detail = new Detail("等待数据"+a,"等待数据","等待数据");
            BlankDataList.add(detail);
        }
        mNewsDataList = BlankDataList;
        adapter = new NewsAdapter(mNewsDataList);
        layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                if (mNewsDataList.get(adapterPosition).getTime().equals("等待数据")){
                    Toast.makeText(getActivity(), "请等待数据刷新后再点击", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(),WebActivity.class);
                    String url  = mNewsDataList.get(adapterPosition).getUrl();
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ///////////////////////////////////////
    }



    @Override
    public void onResume() {
        super.onResume();
        mPresenter.HandleData(null);
        Log.d(TAG, "pppppppppppppppppppppppppppppppp");
        RefreshData();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }


    @Override
    public void show(String t) {

        //TvTest.setText(t);
        Log.d(TAG, "88888"+t);
    }

    @Override
    public void SetAdapterData(ArrayList<Detail> details) {
        mNewsDataList.clear();
        mNewsDataList = details;
        Log.d(TAG, "88888"+details.get(0).getSrc());
        Log.d(TAG, "7777"+mNewsDataList.get(0).getSrc());

        adapter.ChangeAllData(details);

    }




    private void RefreshData(){
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //被刷新时的操作
                //更新UI
                mPresenter.HandleData(null);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //更新成功后设置UI，停止更新
                        //TvTest.setText("刷新成功！！！");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },10);
            }
        });
    }

}
