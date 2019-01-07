package com.bawei.www.mvphttp.presonter;

import com.bawei.www.mvphttp.callback.MyCallback;
import com.bawei.www.mvphttp.model.IModel;
import com.bawei.www.mvphttp.view.IView;

import java.util.Map;

public class Ipresonter implements IP {

    IView iView;
    IModel iModel;

    public Ipresonter(IView iView) {
        this.iView = iView;
        iModel =new IModel();
    }

    @Override
    public void setRequest(String url, Map<String, String> map, Class clazz) {

        iModel.setResponse(url, clazz, map, new MyCallback() {
            @Override
            public void onData(Object data) {

                iView.setSuccess(data);
            }
        });
    }


}
