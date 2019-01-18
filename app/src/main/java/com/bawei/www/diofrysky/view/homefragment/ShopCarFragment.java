package com.bawei.www.diofrysky.view.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.ShopCarRviewAdapter;
import com.bawei.www.diofrysky.bean.AdressBean;
import com.bawei.www.diofrysky.bean.IndentAlllsitBean;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.bean.SerchSaveIntentBean;
import com.bawei.www.diofrysky.bean.SerchSaveShopCarBean;
import com.bawei.www.diofrysky.bean.ShopCarBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;
import com.bawei.www.diofrysky.view.PayIndentActivity;
import com.bawei.www.diofrysky.view.indentfragment.AlllsitFragment;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopCarFragment extends Fragment implements IView {

    @BindView(R.id.shopcar_rview)
    RecyclerView shopcarRview;
    @BindView(R.id.shopcar_checked)
    CheckBox shopcarChecked;
    @BindView(R.id.shopcar_allmoney)
    TextView shopcarAllmoney;
    @BindView(R.id.shopcar_payallmoney)
    Button shopcarPayallmoney;
    Unbinder unbinder;
    private IPersonter iPersonter;
    private ShopCarRviewAdapter shopCarRviewAdapter;
    private List<ShopCarBean.ResultBean> mlist = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home_shopcar, null);
        unbinder = ButterKnife.bind(this, view);
        iPersonter = new IPersonter(this);
        iPersonter.setGetRequest(Apis.SEARCH_SHOPCAR, ShopCarBean.class);
        shopCarRviewAdapter = new ShopCarRviewAdapter(getActivity());
        shopcarRview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        shopcarRview.setAdapter(shopCarRviewAdapter);
        initChecked();
        return view;
    }

    private void initChecked() {
        shopCarRviewAdapter.listener(new ShopCarRviewAdapter.CheckListener() {
            @Override
            public void setSuccess(List<ShopCarBean.ResultBean> list) {

               double price = 0;
                boolean flag = true;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isIschecked()) {
                        price = price + (list.get(i).getCount() * list.get(i).getPrice());
                    } else if (list.get(i).isIschecked() == false) {
                        flag = false;
                    }
                }

                if (flag == false) {
                    shopcarChecked.setChecked(false);
                } else if (flag == true) {
                    shopcarChecked.setChecked(true);
                }
                shopcarAllmoney.setText("总价为：￥" + price);
            }
        });
    }

    @Override
    public void setSuccess(Object data) {
     if(data instanceof ShopCarBean){
         ShopCarBean shopCarBean = (ShopCarBean) data;
         //Toast.makeText(getActivity(),""+shopCarBean.getMessage(),Toast.LENGTH_SHORT).show();
         mlist = shopCarBean.getResult();
         shopCarRviewAdapter.setData(mlist);
     }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.shopcar_checked, R.id.shopcar_payallmoney})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopcar_checked:
                checkSeller(shopcarChecked.isChecked());
                break;
            case R.id.shopcar_payallmoney:
                boolean flag1=false;
                for (int i = 0; i <mlist.size() ; i++) {
                    if(mlist.get(i).isIschecked()){
                       flag1=true;
                       break;
                    }
                }
                if(flag1==true){
                    EventBus.getDefault().postSticky(mlist);
                    startActivity(new Intent(getActivity(),PayIndentActivity.class));
                }else {
                    Toast.makeText(getActivity(),"请选择商品！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



    private void checkSeller(boolean checked) {

        double price = 0;

        for (int i = 0; i <mlist.size() ; i++) {
            ShopCarBean.ResultBean resultBean = mlist.get(i);
            resultBean.setIschecked(checked);
            price = price +(mlist.get(i).getCount()*mlist.get(i).getPrice());
        }

        shopCarRviewAdapter.setChecked(checked);

        if (checked) {
            shopcarAllmoney.setText("总价为：￥" + price);
        } else{
            shopcarAllmoney.setText("总价为：￥ 0. 00");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==100){
//            for (int i = 0; i <mlist.size() ; i++) {
//                if(mlist.get(i).isIschecked()){
//                    mlist.remove(i);
//            }
//            }
//            shopCarRviewAdapter.setData(mlist);
//        }
    }
}
