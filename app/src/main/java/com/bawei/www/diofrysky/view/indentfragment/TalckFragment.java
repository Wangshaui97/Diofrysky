package com.bawei.www.diofrysky.view.indentfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.ToChat_Adapter;
import com.bawei.www.diofrysky.RecycViewAdatpter.ToGet_Adapter;
import com.bawei.www.diofrysky.bean.IndentAlllsitBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TalckFragment extends Fragment implements IView {

    @BindView(R.id.indent_talck_rview)
    XRecyclerView indentTalckRview;
    Unbinder unbinder;
    private IPersonter iPersonter;
    private int page;
    private ToGet_Adapter toGet_adapter;
    private ToChat_Adapter toChat_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_talck, null);
        unbinder = ButterKnife.bind(this, view);
        iPersonter = new IPersonter(this);
        page=1;
        iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT_NOPAY,3,page), IndentAlllsitBean.class);

        return view;
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

    @Override
    public void setSuccess(Object data) {
        IndentAlllsitBean allListIndent_info = (IndentAlllsitBean) data;
        toChat_adapter = new ToChat_Adapter(getContext());
        List<IndentAlllsitBean.OrderListBean> orderList = allListIndent_info.getOrderList();
        toChat_adapter.setOrderListBeans(orderList);
        indentTalckRview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        indentTalckRview.setAdapter(toChat_adapter);
    }
}
