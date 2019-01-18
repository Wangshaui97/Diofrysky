package com.bawei.www.diofrysky.view.indentfragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.IndentRviewAdapter;
import com.bawei.www.diofrysky.bean.IndentAlllsitBean;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NopayFragment extends Fragment implements IView {

    @BindView(R.id.indent_nopay_rview)
    XRecyclerView indentNopayRview;
    Unbinder unbinder;
    private IPersonter iPersonter;
    private IndentRviewAdapter indentRviewAdapter;
    private int page;
    private String goodsId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_nopay, null);
        unbinder = ButterKnife.bind(this, view);
        iPersonter =new IPersonter(this);
        indentNopayRview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        indentRviewAdapter = new IndentRviewAdapter(getActivity());
        indentNopayRview.setAdapter(indentRviewAdapter);
        page=1;
        iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT_NOPAY,1,page), IndentAlllsitBean.class);
        initDel();
        indentNopayRview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT,page), IndentAlllsitBean.class);
                        indentNopayRview.refreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                indentNopayRview.refreshComplete();
            }
        });

        return view;
    }

    private void initDel() {
        indentRviewAdapter.listener(new IndentRviewAdapter.CheckListener() {
            @Override
            public void setSuccess(String orderId) {
                goodsId = orderId;
                iPersonter.setDelRequest(String.format(Apis.DELETE_INDENT,orderId),LoginBean.class);
            }
        });

        indentRviewAdapter.listener(new IndentRviewAdapter.PayCheckListener() {
            @Override
            public void setSuccess(String orderId) {
                Map<String,String> map = new HashMap<>();
                map.put("orderId",orderId);
                map.put("payType","1");
                iPersonter.setPostRequest(Apis.PAY_FOR_INDENT,map,LoginBean.class);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iPersonter != null) {
            iPersonter.onDestich();
        }
    }


    @Override
    public void setSuccess(Object data) {
        if(data instanceof IndentAlllsitBean){
            IndentAlllsitBean indentAlllsitBean = (IndentAlllsitBean) data;
            List<IndentAlllsitBean.OrderListBean> orderList = indentAlllsitBean.getOrderList();
            indentRviewAdapter.setData(orderList);
        }else if(data instanceof LoginBean){
            LoginBean loginBean = (LoginBean) data;
            if(loginBean.getMessage().equals("删除成功")){
                Toast.makeText(getActivity(),""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
                iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT,1), IndentAlllsitBean.class);
            }
            if(loginBean.getMessage().equals("支付成功")){
               iPersonter.setDelRequest(String.format(Apis.DELETE_INDENT,goodsId),LoginBean.class);
               Toast.makeText(getActivity(),""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
