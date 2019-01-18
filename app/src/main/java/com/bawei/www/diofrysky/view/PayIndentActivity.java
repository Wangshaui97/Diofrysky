package com.bawei.www.diofrysky.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.IndentRviewitemAdapter;
import com.bawei.www.diofrysky.RecycViewAdatpter.PayIndentAdapter;
import com.bawei.www.diofrysky.RecycViewAdatpter.ShopCarRviewAdapter;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.bean.AdressBean;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.bean.PayIndentBean;
import com.bawei.www.diofrysky.bean.SerchSaveIntentBean;
import com.bawei.www.diofrysky.bean.ShopCarBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayIndentActivity extends BaseActivity implements IView {
    @BindView(R.id.payindent_adress_name)
    TextView payindentAdressName;
    @BindView(R.id.payindent_adress_phone)
    TextView payindentAdressPhone;
    @BindView(R.id.payindent_adress_adress)
    TextView payindentAdressAdress;
    @BindView(R.id.rr)
    RelativeLayout rr;
    @BindView(R.id.payindent_adress_rview)
    RecyclerView payindentAdressRview;
    @BindView(R.id.payindent_adress_pay)
    Button payindentAdressPay;
    private IPersonter iPersonter;
    private List<SerchSaveIntentBean> slist;
    private double price;
    private List<ShopCarBean.ResultBean> nlist;
    private int id;

    @Override
    protected int initContextView() {
        return R.layout.activity_payindent;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        iPersonter = new IPersonter(this);
        iPersonter.setGetRequest(Apis.SEARCH_ADRESS,AdressBean.class);
    }

    @Override
    protected void initData() {
        PayIndentAdapter payIndentAdapter = new PayIndentAdapter(this);
        payindentAdressRview.setLayoutManager(new LinearLayoutManager(PayIndentActivity.this, LinearLayoutManager.VERTICAL, false));
        payindentAdressRview.setAdapter(payIndentAdapter);
        payIndentAdapter.setData(nlist);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void msg(List<ShopCarBean.ResultBean> mlist) {
        price = 0;
        slist = new ArrayList<>();
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).isIschecked()) {
                slist.add(new SerchSaveIntentBean(mlist.get(i).getCommodityId(), mlist.get(i).getCount()));
                price = price + (mlist.get(i).getCount() * mlist.get(i).getPrice());
            }
        }

        nlist = new ArrayList<>();

        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).isIschecked()) {
                nlist.add(mlist.get(i));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.payindent_adress_pay)
    public void onViewClicked() {
        String toJson = new Gson().toJson(slist);
        Map<String, String> map = new HashMap<>();
        map.put("orderInfo", toJson);
        map.put("totalPrice", price + "");
        map.put("addressId", ""+id);
        iPersonter.setPostRequest(Apis.PUTIN_INDENT, map, LoginBean.class);
        setResult(100);
        finish();
    }

    @Override
    public void setSuccess(Object data) {
       if(data instanceof LoginBean){
           LoginBean loginBean = (LoginBean) data;
           Toast.makeText(PayIndentActivity.this, "" + loginBean.getMessage(), Toast.LENGTH_SHORT).show();

       }else if(data instanceof AdressBean){
           AdressBean adressBean = (AdressBean) data;
           List<AdressBean.ResultBean> result = adressBean.getResult();
           for (int i = 0; i <result.size() ; i++) {
             // if(result.get(i).isChecked()){
                   id = result.get(i).getId();
              // }
           }
       }
    }
}
