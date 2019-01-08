package com.bawei.www.diofrysky.view.homefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.EventBusMassager;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.bean.PeopleCirleBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.DetailsActivity;
import com.bawei.www.diofrysky.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PeopleCircleFragment extends Fragment implements IView {


    @BindView(R.id.people_cirle_rview)
    RecyclerView peopleCirleRview;
    Unbinder unbinder;
    private IPersonter iPersonter;
    private int page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_peoplecircle, container, false);
        unbinder = ButterKnife.bind(this, view);
        page=1;
        iPersonter = new IPersonter(this);

        iPersonter.setGetRequest(String.format(Apis.PEOPLE_CIRLE_URL,page),PeopleCirleBean.class);

        return view;
    }

    @Override
    public void setSuccess(Object data) {
        PeopleCirleBean peopleCirleBean= (PeopleCirleBean) data;

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
