package com.bawei.www.mvphttp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bawei.www.mvphttp.Apis;
import com.bawei.www.mvphttp.R;
import com.bawei.www.mvphttp.bean.LoginBean;
import com.bawei.www.mvphttp.presonter.Ipresonter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {

    private Ipresonter ipresonter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipresonter = new Ipresonter(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData() {
        Map<String,String> map =new HashMap<>();
        map.put("phone","18811795060");
        map.put("pwd","black.97");
        ipresonter.setRequest(Apis.LOGIN_URL,map,LoginBean.class);

    }

    @Override
    public void setSuccess(Object data) {
        LoginBean loginBean = (LoginBean) data;
        Toast.makeText(MainActivity.this,""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
