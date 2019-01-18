package com.bawei.www.diofrysky.view.indentfragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AlllsitFragment extends Fragment implements IView {

    @BindView(R.id.indent_alllist_rview)
    XRecyclerView indentAlllistRview;
    Unbinder unbinder;
    private IPersonter iPersonter;
    private IndentRviewAdapter indentRviewAdapter;
    private int page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_alllsit, null);
        iPersonter = new IPersonter(this);
        unbinder = ButterKnife.bind(this, view);
        indentAlllistRview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        indentRviewAdapter = new IndentRviewAdapter(getActivity());
        indentAlllistRview.setAdapter(indentRviewAdapter);
        page=1;
        iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT_NOPAY,0,page), IndentAlllsitBean.class);
        initDel();
        indentAlllistRview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT,page), IndentAlllsitBean.class);
                        indentAlllistRview.refreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                indentAlllistRview.refreshComplete();
            }
        });

        return view;
    }



    private void initDel() {
        indentRviewAdapter.listener(new IndentRviewAdapter.CheckListener() {
            @Override
            public void setSuccess(String orderId) {
                iPersonter.setDelRequest(String.format(Apis.DELETE_INDENT,orderId),LoginBean.class);
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
           Toast.makeText(getActivity(),""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
           iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT,1), IndentAlllsitBean.class);
       }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
