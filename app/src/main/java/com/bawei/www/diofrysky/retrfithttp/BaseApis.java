package com.bawei.www.diofrysky.retrfithttp;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface BaseApis<T> {

    @GET
    Observable<ResponseBody> get(@Url String url);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> map);

    @Multipart
    @POST
    Observable<ResponseBody> postRequestBody(@Url String url, @PartMap Map<String, RequestBody> map);

}
