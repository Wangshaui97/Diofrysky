package com.bawei.www.diofrysky.presonter;

import com.bawei.www.diofrysky.callback.MyCallback;
import com.bawei.www.diofrysky.model.IModel;
import com.bawei.www.diofrysky.view.IView;

import java.util.Map;

public class IPersonter implements IP {

    IView iView;
    IModel iModel;

    public IPersonter(IView iView) {
        this.iView = iView;
        iModel = new IModel();
    }

    @Override
    public void setPostRequest(String url, Map<String, String> map, Class clazz) {
        iModel.setPostResponse(url, map, clazz, new MyCallback() {
            @Override
            public void setData(Object data) {
                iView.setSuccess(data);
            }
        });
    }

    @Override
    public void setPutRequest(String url, Map<String, String> map, Class clazz) {
        iModel.setPutResponse(url, map, clazz, new MyCallback() {
            @Override
            public void setData(Object data) {
                iView.setSuccess(data);
            }
        });
    }

    @Override
    public void setGetRequest(String url, Class clazz) {
        iModel.setGetResponse(url, clazz, new MyCallback() {
            @Override
            public void setData(Object data) {
                iView.setSuccess(data);
            }
        });
    }


    public void onDestich() {
        if (iView != null) {
            iView = null;
        }
        if (iModel != null) {
            iModel = null;
        }
    }
}
