package com.bawei.www.diofrysky;


import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

    private static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mcontext =getApplicationContext();
    }

    public static Context getApplication(){
     return mcontext;
    }

}
