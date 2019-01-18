package com.bawei.www.diofrysky.retrfithttp;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpUtil {

    private static HttpUtil mHttpUtil;
    private final Handler handler;
    private final OkHttpClient client;

    public HttpUtil() {
        handler = new Handler(Looper.myLooper());
        client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
    }

    public static HttpUtil getInterface() {
        if (mHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (mHttpUtil == null) {
                    mHttpUtil = new HttpUtil();
                }
            }
        }
        return mHttpUtil;
    }

    public interface HttpUtilInterface {

        void HttpFailure();
        void HttpResponse(String json);
    }

    public void doPost(String url, Map<String, String> map, final HttpUtilInterface httpUtilInterface) {
        FormBody.Builder form = new FormBody.Builder();

        if (map != null) {
            for (String key : map.keySet()) {
                form.add(key, map.get(key));
            }
        }

        FormBody body = form.build();

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpResponse(json);
                    }
                });
            }
        });
    }

    //封装doGEt的网络封装,参数定义两个,一个是URL网址   一个实现接口的对象
    public void doGet(String url, final HttpUtilInterface ycfOkhttpCallback) {
        //创建FormBody对象,把表单添加到FormBody
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (ycfOkhttpCallback != null) {
                    //切换到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ycfOkhttpCallback.HttpFailure();
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String json = response.body().string();
                    if (ycfOkhttpCallback != null) {
                        //切换到主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ycfOkhttpCallback.HttpResponse(json);
                            }
                        });
                    }
                }
            }
        });
    }

    public void doHeadGet(String url, String userId, String sessionId, final HttpUtilInterface ycfOkhttpCallback) {
//创建FormBody对象,把表单添加到FormBody
        Request request = new Request.Builder()
                .get()
                .addHeader("userId", userId)
                .addHeader("sessionId", sessionId)
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (ycfOkhttpCallback != null) {
                    //切换到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ycfOkhttpCallback.HttpFailure();
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String json = response.body().string();
                    if (ycfOkhttpCallback != null) {
                        //切换到主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ycfOkhttpCallback.HttpResponse(json);
                            }
                        });
                    }
                }
            }
        });
    }

    public void doHeadPost(String url, Map<String, String> map, String userId, String sessionId, final HttpUtilInterface httpUtilInterface) {
        FormBody.Builder form = new FormBody.Builder();

        if (map != null) {
            for (String key : map.keySet()) {
                form.add(key, map.get(key));
            }
        }

        FormBody body = form.build();

        Request request = new Request.Builder()
                .post(body)
                .addHeader("userId", userId)
                .addHeader("sessionId", sessionId)
                .url(url)
                .build();
        /*Log.e("评论",""+map);
        Log.e("评论",""+body.toString());*/

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpResponse(json);
                    }
                });
            }
        });
    }

    public void doHeadPut(String url, Map<String, String> map, String userId, String sessionId, final HttpUtilInterface httpUtilInterface) {
        FormBody.Builder form = new FormBody.Builder();

        if (map != null) {
            for (String key : map.keySet()) {
                form.add(key, map.get(key));
            }
        }

        FormBody body = form.build();

        Request request = new Request.Builder()
                .addHeader("userId", userId)
                .addHeader("sessionId", sessionId)
                .put(body)
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpResponse(json);
                    }
                });
            }
        });
    }

    public void doHeadPostImage(String url, File img, String userId, String sessionId, final HttpUtilInterface httpUtilInterface){
        //RequestBody fileBody = RequestBody.create(MediaType.parse("image.png"), img);

        MultipartBody responseBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", img.getName(), RequestBody.create(MediaType.parse("image/png"), img))
                .build();
        Log.e("URL_",url);
        Request request = new Request.Builder()
                .post(responseBody)
                .addHeader("userId", userId)
                .addHeader("sessionId", sessionId)
                .url(url)
                .build();
        Log.e("URL_",request.body().toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpResponse(json);
                    }
                });
            }
        });
    }
    public void doHeadDelete(String url, String userId, String sessionId, final HttpUtilInterface httpUtilInterface){
        Request request = new Request.Builder()
                .delete()
                .addHeader("userId", userId)
                .addHeader("sessionId", sessionId)
                .url(url)
                .build();
        Log.e("URL_",request.body().toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpResponse(json);
                    }
                });
            }
        });
    }

    public void doHeadPostDelete(String url, Map<String, String> map, String userId, String sessionId, final HttpUtilInterface httpUtilInterface){

        FormBody.Builder form = new FormBody.Builder();

        if (map != null) {
            for (String key : map.keySet()) {
                form.add(key, map.get(key));
            }
        }

        FormBody body = form.build();

        Request request = new Request.Builder()
                .delete()
                .addHeader("userId", userId)
                .addHeader("sessionId", sessionId)
                .url(url)
                .delete(body)
                .build();
        Log.e("URL_",request.body().toString());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpUtilInterface.HttpResponse(json);
                    }
                });
            }
        });
    }
}
