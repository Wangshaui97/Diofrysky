package com.bawei.www.mvphttp.model;

import com.bawei.www.mvphttp.callback.MyCallback;

import java.util.Map;

public interface IM {
    void setResponse(String url, Class clazz, Map<String,String> map, MyCallback myCallback);
}
