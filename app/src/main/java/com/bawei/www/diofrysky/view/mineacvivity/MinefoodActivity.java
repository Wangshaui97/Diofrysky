package com.bawei.www.diofrysky.view.mineacvivity;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.MinefoodAdapter;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.bean.FoodsBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinefoodActivity extends BaseActivity implements IView {


    @BindView(R.id.mine_foods_rview)
    RecyclerView mineFoodsRview;
    private IPersonter iPersonter;
    private MinefoodAdapter minefoodAdapter;

    @Override
    protected int initContextView() {
        return R.layout.activity_minefood;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        iPersonter = new IPersonter(this);
        iPersonter.setGetRequest(Apis.MINE_FOODS,FoodsBean.class);
        mineFoodsRview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        minefoodAdapter = new MinefoodAdapter(this);
        mineFoodsRview.setAdapter(minefoodAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setSuccess(Object data) {
        FoodsBean foodsBean = (FoodsBean) data;
        List<FoodsBean.ResultBean> result = foodsBean.getResult();
        minefoodAdapter.setData(result);
    }
}
