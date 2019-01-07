package com.bawei.www.diofrysky.presonter;

import java.util.Map;

public interface IP {
    void setPostRequest(String url, Map<String, String> map,Class clazz);
    void setGetRequest(String url,Class clazz);
}
