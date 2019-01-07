package com.bawei.www.diofrysky.view;



import android.widget.Toast;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.bean.EventBusMassager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailsActivity extends BaseActivity implements IView {


    @Override
    protected int initContextView() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        initClick();
    }

    private void initClick() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void massage(EventBusMassager eventBusMassager){
        int index = eventBusMassager.getIndex();
        Toast.makeText(DetailsActivity.this,""+index,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setSuccess(Object data) {

    }
}
