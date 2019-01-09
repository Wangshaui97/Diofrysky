package com.bawei.www.diofrysky.view.homefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.PeopleCirleAdatpter;
import com.bawei.www.diofrysky.bean.PeopleCirleBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PeopleCircleFragment extends Fragment implements IView {


    @BindView(R.id.people_cirle_rview)
    RecyclerView peopleCirleRview;
    Unbinder unbinder;
    private IPersonter iPersonter;
    private int page;
    private PeopleCirleAdatpter peopleCirleAdatpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_peoplecircle, container, false);
        unbinder = ButterKnife.bind(this, view);
        page=1;
        iPersonter = new IPersonter(this);
        iPersonter.setGetRequest(String.format(Apis.PEOPLE_CIRLE_URL,page),PeopleCirleBean.class);
        peopleCirleRview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        peopleCirleAdatpter = new PeopleCirleAdatpter(getActivity());
        peopleCirleRview.setAdapter(peopleCirleAdatpter);
        return view;
    }

    @Override
    public void setSuccess(Object data) {
        PeopleCirleBean peopleCirleBean= (PeopleCirleBean) data;
        List<PeopleCirleBean.ResultBean> beanResult = peopleCirleBean.getResult();
        peopleCirleAdatpter.setData(beanResult);
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
