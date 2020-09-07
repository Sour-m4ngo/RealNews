package com.example.realnews.BaseMVP;

import com.example.realnews.MainContract;

public  abstract class BaseModel implements MainContract.IMainModel {

    /**
     * @description 校验接口合法性
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public <T> void validateServiceInterface(Class<T> service) {
        if (service == null) {
            //AppToast.ShowToast("服务接口不能为空！");
        }
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }
}
