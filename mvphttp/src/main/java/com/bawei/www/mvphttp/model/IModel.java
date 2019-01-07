package com.bawei.www.mvphttp.model;

import android.util.Log;

import com.bawei.www.mvphttp.callback.MyCallback;
import com.bawei.www.mvphttp.retrfithttp.RetrotifHtpp;
import com.google.gson.Gson;

import java.util.Map;

public class IModel implements IM {

    @Override
    public void setResponse(String url, final Class clazz, Map<String, String> map, final MyCallback myCallback) {
        //网络请求成功   obj

        RetrotifHtpp.getInstance().post(url,map).listener(new RetrotifHtpp.HttpListener() {
            @Override
            public void setSuccess(String data) {
                try {
                    Object obj = new Gson().fromJson(data, clazz);
                    if(myCallback!=null){
                        myCallback.onData(obj);
                    }
                }catch (Exception e){
                    Log.d("WS",e+"123");
                }
            }

            @Override
            public void SetField(String e) {
                Log.d("WS",e);
            }
        });


    }

}
