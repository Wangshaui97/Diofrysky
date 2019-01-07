package com.bawei.www.mvphttp.retrfithttp;

import com.bawei.www.mvphttp.Apis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrotifHtpp<T> {

    private static RetrotifHtpp mretrotifHtpp;

    public static synchronized RetrotifHtpp getInstance(){
        if(mretrotifHtpp==null){
            mretrotifHtpp = new RetrotifHtpp();
        }
        return mretrotifHtpp;
    }

    private BaseApis mbaseApis;

    public RetrotifHtpp(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10,TimeUnit.SECONDS);
        builder.readTimeout(10,TimeUnit.SECONDS);
        builder.writeTimeout(10,TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.retryOnConnectionFailure(true);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Apis.BASE_URL)
                .client(client)
                .build();

        mbaseApis = retrofit.create(BaseApis.class);

    }

    public RetrotifHtpp get(String url){

        mbaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return mretrotifHtpp;
    }

    public RetrotifHtpp post(String url, Map<String,String> map){

        if(map==null){
            map = new HashMap<>();
        }

        mbaseApis.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return mretrotifHtpp;
    }


    public Observer observer = new Observer<ResponseBody>() {

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data = responseBody.string();

                if(listener!=null){
                    listener.setSuccess(data);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if(listener!=null){
                listener.SetField(e.getMessage());
            }
        }


    };


    private HttpListener listener;

    public interface HttpListener{
        void setSuccess(String data);
        void SetField(String e);
    }
    public void listener(HttpListener Clicklistenr){
        this.listener = Clicklistenr;
    }



}
