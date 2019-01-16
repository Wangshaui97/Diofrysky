package com.bawei.www.diofrysky.retrfithttp;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface BaseApis<T> {

    @GET
    Observable<ResponseBody> get(@Url String url);

    @DELETE
    Observable<ResponseBody> del(@Url String url);


    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> map);

    @Multipart
    @POST
    Observable<ResponseBody> postRequestBody(@Url String url, @PartMap Map<String, RequestBody> map);

    @PUT
    Observable<ResponseBody> put(@Url String url ,@QueryMap Map<String ,String> map);

    @POST
    Observable<ResponseBody> Image(@Url String url, @HeaderMap Map<String,Object> headermap, @Body MultipartBody body);

}
