package com.bawei.www.diofrysky.presonter;

import com.bawei.www.diofrysky.callback.MyCallback;

import java.util.List;
import java.util.Map;

public interface IP {
    void setPostRequest(String url, Map<String, String> map,Class clazz);
    void setPutRequest(String url, Map<String, String> map,Class clazz);
    void setGetRequest(String url,Class clazz);
    void setDelRequest(String url,Class clazz);
    void getrequestimgtitle(String url, Map<String,Object> map, List<Object> list, Class clazz);
}
