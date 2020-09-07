package com.example.realnews.BaseInterface;

public interface IBasePresenter<V extends IBaseView> {
    void attach(V view);

    void detach();

    /**
     * @description RX订阅
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void subscribe();

    /**
     * @description RX取消订阅
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void unsubscribe();
}
