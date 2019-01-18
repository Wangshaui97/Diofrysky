package com.bawei.www.diofrysky.view.mineacvivity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.PeopleCirleAdatpter;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.bean.PeopleCirleBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineCiecleActivity extends BaseActivity implements IView {
    @BindView(R.id.mine_circle)
    RecyclerView mineCircle;
    private IPersonter iPersonter;
    private PeopleCirleAdatpter peopleCirleAdatpter;

    @Override
    protected int initContextView() {
        return R.layout.activity_mine_ciecle;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        iPersonter = new IPersonter(this);
        iPersonter.setGetRequest(Apis.MINE_CRICLE,PeopleCirleBean.class);
    }

    @Override
    protected void initData() {
        mineCircle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        peopleCirleAdatpter = new PeopleCirleAdatpter(this);
        mineCircle.setAdapter(peopleCirleAdatpter);
    }


    @Override
    public void setSuccess(Object data) {
        PeopleCirleBean peopleCirleBean = (PeopleCirleBean) data;
        List<PeopleCirleBean.ResultBean> result = peopleCirleBean.getResult();
        peopleCirleAdatpter.setData(result);
    }
}
