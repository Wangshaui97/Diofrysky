package com.bawei.www.diofrysky.model;

import com.bawei.www.diofrysky.callback.MyCallback;

import java.util.List;
import java.util.Map;

public interface IM {
    void setPostResponse(String url, Map<String, String> map,Class clazz, MyCallback myCallback);
    void setPutResponse(String url, Map<String, String> map,Class clazz, MyCallback myCallback);
    void setGetResponse(String url,Class clazz, MyCallback myCallback);
    void setDelResponse(String url,Class clazz, MyCallback myCallback);
    void getimgtitle(String url, Map<String,Object> map, List<Object> list,Class clazz,MyCallback myCallback);
}
