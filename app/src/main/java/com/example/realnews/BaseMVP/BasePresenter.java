package com.example.realnews.BaseMVP;

import com.example.realnews.BaseInterface.IBasePresenter;
import com.example.realnews.BaseInterface.IBaseView;


import java.lang.ref.SoftReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public abstract class BasePresenter <V extends IBaseView, M extends BaseModel> implements IBasePresenter {
    private SoftReference<IBaseView> mReferenceView;
    private  M mModel;
    private CompositeDisposable mCompositeDisposable;
    @SuppressWarnings("unchecked")
    @Override
    public void attach(IBaseView view) {
        mReferenceView = new SoftReference<>(view);

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        if (type != null) {
            Type[] types = type.getActualTypeArguments();
            try {
                mModel = (M) ((Class<?>) types[1]).newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }
    @SuppressWarnings("unchecked")//设置编译器忽略“unchecked警告
    public V getView(){
        return (V) mReferenceView.get();
    }
    public M getmModel (){
        return mModel;
    }
    @Override
    public void detach() {
        mReferenceView.clear();
        mReferenceView = null;
    }
    @Override
    public void unsubscribe(){

    }
    public void addSubscription(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    public void addDisposable(Disposable subscription) {
        //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    public void CancelSubscription(){
        mCompositeDisposable.dispose();
    }
}
