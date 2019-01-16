package com.bawei.www.diofrysky.retrfithttp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.App;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrfitHttp<T> {


    private static RetrfitHttp mretrfitHttp;

    public static synchronized RetrfitHttp getInsetens() {
        if (mretrfitHttp == null) {
            mretrfitHttp = new RetrfitHttp();
        }
        return mretrfitHttp;
    }

    private BaseApis mbaseApis;

    public RetrfitHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
                                   @Override
                                   public Response intercept(Chain chain) throws IOException {

                                       Request request = chain.request();

                                       SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("user", Context.MODE_PRIVATE);
                                       String userId = sharedPreferences.getString("userId", "");
                                       String sessionId = sharedPreferences.getString("sessionId", "");

                                       Request.Builder newBuilder = request.newBuilder();
                                       newBuilder.method(request.method(), request.body());

                                       if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(sessionId)) {
                                           newBuilder.addHeader("userId", userId);
                                           newBuilder.addHeader("sessionId", sessionId);
                                       }

                                       Request build = newBuilder.build();

                                       return chain.proceed(build);
                                   }
                               }


        );
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Apis.BASE_URL)
                .client(client)
                .build();

        mbaseApis = retrofit.create(BaseApis.class);
    }


    public void get(String url, HttpListener listener) {

        mbaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));

    }
    public void del(String url, HttpListener listener) {

        mbaseApis.del(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));

    }
    public void post(String url, Map<String, String> map, HttpListener listener) {

        if (map == null) {
            map = new HashMap<>();
        }
        mbaseApis.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));

    }

    public void postFormBodyObject(String url, Map<String,Object> params, List<Object> list,HttpListener listener){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list.size()==1) {
            for (int i = 0; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("image", file.getName(),RequestBody.create(MediaType.parse("multipart/octet-stream"),file));
            }
        }
        mbaseApis.Image(url,params,builder.build())
                .subscribeOn(Schedulers.io

                        ())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));
    }


    public void put(String url, Map<String, String> map, HttpListener listener) {

        if (map == null) {
            map = new HashMap<>();
        }
        mbaseApis.put(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));
    }



//    public Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
//        Map<String, RequestBody> requestBodyMap = new HashMap<>();
//        for (String key : requestDataMap.keySet()) {
//            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
//                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
//            requestBodyMap.put(key, requestBody);
//        }
//        return requestBodyMap;
//    }
//
//

    private Observer getobserver(final HttpListener listener) {

        Observer observer = new Observer<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String data = responseBody.string();
                    if (listener != null) {
                        listener.setSuccess(data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.SetField(e.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (listener != null) {
                    listener.SetField(e.getMessage());
                }
            }


            @Override
            public void onCompleted() {

            }
        };
        return observer;
    }


    private HttpListener listener;

    public interface HttpListener {
        void setSuccess(String data);

        void SetField(String e);
    }

    public void listener(HttpListener Clicklistenr) {
        this.listener = Clicklistenr;
    }

}
