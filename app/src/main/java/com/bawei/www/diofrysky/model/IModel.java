package com.bawei.www.diofrysky.model;

import android.util.Log;

import com.bawei.www.diofrysky.callback.MyCallback;
import com.bawei.www.diofrysky.retrfithttp.RetrfitHttp;
import com.google.gson.Gson;

import java.util.Map;

public class IModel implements IM {

    @Override
    public void setPostResponse(String url, Map<String, String> map, final Class clazz, final MyCallback myCallback) {
        RetrfitHttp.getInsetens().post(url,map).listener(new RetrfitHttp.HttpListener() {
            @Override
            public void setSuccess(String data) {
                Object obj=new Gson().fromJson(data,clazz);
                try {
                    if(myCallback!=null){
                        myCallback.setData(obj);
                    }
                }catch (Exception e){
                    Log.d("WS","111"+e);
                }
            }
            @Override
            public void SetField(String e) {
                Log.d("WS",e);
            }
        });
    }

    @Override
    public void setGetResponse(String url, final Class clazz, final MyCallback myCallback) {
        RetrfitHttp.getInsetens().get(url).listener(new RetrfitHttp.HttpListener() {
            @Override
            public void setSuccess(String data) {
                Object obj=new Gson().fromJson(data,clazz);
                try {
                    if(myCallback!=null){
                        myCallback.setData(obj);
                    }
                }catch (Exception e){
                    Log.d("WS","111"+e);
                }
            }
            @Override
            public void SetField(String e) {
                Log.d("WS",e);
            }
        });
    }


}
