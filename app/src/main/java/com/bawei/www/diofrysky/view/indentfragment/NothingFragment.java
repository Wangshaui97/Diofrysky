package com.bawei.www.diofrysky.view.indentfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.IndentAlllsitBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NothingFragment extends Fragment implements IView {

    @BindView(R.id.indent_nothing_rview)
    XRecyclerView indentNothingRview;
    Unbinder unbinder;
//    private IPersonter iPersonter;
//    private int page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_nothing, null);
        unbinder = ButterKnife.bind(this, view);
//        iPersonter = new IPersonter(this);
//        page=1;
//        iPersonter.setGetRequest(String.format(Apis.SEARCH_INDENT,page), IndentAlllsitBean.class);
        return view;
    }

    @Override
    public void setSuccess(Object data) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
