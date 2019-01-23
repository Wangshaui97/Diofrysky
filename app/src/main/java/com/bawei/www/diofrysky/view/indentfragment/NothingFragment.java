package com.bawei.www.diofrysky.view.indentfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.ToGet_Adapter;
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

public class NothingFragment extends Fragment implements IView {

    @BindView(R.id.indent_nothing_rview)
    XRecyclerView indentNothingRview;
    Unbinder unbinder;
    private IPersonter iPersonter;
    private int page;
    private ToGet_Adapter toGet_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_nothing, null);
        unbinder = ButterKnife.bind(this, view);
        iPersonter = new IPersonter(this);
        page=1;
        iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT_NOPAY,2,page), IndentAlllsitBean.class);
        return view;
    }

    @Override
    public void setSuccess(Object data) {
        if(data instanceof IndentAlllsitBean){
            IndentAlllsitBean allListIndent_info = (IndentAlllsitBean) data;
            toGet_adapter = new ToGet_Adapter(getContext());
            List<IndentAlllsitBean.OrderListBean> orderList = allListIndent_info.getOrderList();
            toGet_adapter.setOrderListBeans(orderList);
            indentNothingRview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            indentNothingRview.setAdapter(toGet_adapter);

        }
        if(data instanceof LoginBean){
            LoginBean loginBean = (LoginBean) data;
            Toast.makeText(getActivity(),""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }

        toGet_adapter.setOnclickId(new ToGet_Adapter.OnclickId() {
            @Override
            public void onSuccess(String orderId) {
                Map<String,String> map=new HashMap<>();
                map.put("orderId",orderId);
                iPersonter.setPutRequest(Apis.shou_Path,map,LoginBean.class);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(iPersonter!=null){
            iPersonter.onDestich();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
