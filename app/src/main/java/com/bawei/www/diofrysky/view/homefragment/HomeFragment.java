package com.bawei.www.diofrysky.view.homefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.HomeRecycViewAdapter;
import com.bawei.www.diofrysky.RecycViewAdatpter.SerchViewAdapter;
import com.bawei.www.diofrysky.bean.HomeBannerBean;
import com.bawei.www.diofrysky.bean.HomeHotBean;
import com.bawei.www.diofrysky.bean.HomeSerchBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IView {
    
    @BindView(R.id.common_home_btn_menu)
    ImageView commonHomeBtnMenu;
    @BindView(R.id.common_nav_btn_puntinsearchname)
    EditText commonNavBtnPuntinsearchname;
    @BindView(R.id.common_nav_btn_search)
    TextView commonNavBtnSearch;
    @BindView(R.id.common_home_rview_one)
    RecyclerView commonHomeRviewOne;
    @BindView(R.id.common_home_rview_two)
    RecyclerView commonHomeRviewTwo;
    @BindView(R.id.common_home_rview_three)
    RecyclerView commonHomeRviewThree;
    Unbinder unbinder;
    @BindView(R.id.home_btn_more_rxxp)
    ImageView homeBtnMoreRxxp;
    @BindView(R.id.home_btn_more_mlss)
    ImageView homeBtnMoreMlss;
    @BindView(R.id.home_btn_more_pzsh)
    ImageView homeBtnMorePzsh;
    @BindView(R.id.scrol)
    ScrollView scrol;
    @BindView(R.id.common_home_rview_serch)
    RecyclerView commonHomeRviewSerch;
    @BindView(R.id.common_home_btn_return)
    ImageView commonHomeBtnReturn;
    @BindView(R.id.xbanner_home)
    XBanner xbannerHome;
    @BindView(R.id.common_home_none_nothing)
    LinearLayout commonHomeNoneNothing;
    private IPersonter iPersonter;
    private HomeRecycViewAdapter homeRecycViewAdapter;
    private SerchViewAdapter serchViewAdapter;
    private List<String> mImgUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        iPersonter = new IPersonter(this);

        initData();

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initBanner() {
        mImgUrl = new ArrayList<>();
        iPersonter.setGetRequest(Apis.HOME_GOODS_BANNER, HomeBannerBean.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iPersonter != null) {
            iPersonter.onDestich();
        }
    }

    private void initData() {
        iPersonter.setGetRequest(Apis.HOME_GOODS_THINGS, HomeHotBean.class);
    }


    @Override
    public void setSuccess(Object data) {
        if (data instanceof HomeHotBean) {
            HomeHotBean homeHotBean = (HomeHotBean) data;

            List<HomeHotBean.ResultBean.MlssBean> mlss = homeHotBean.getResult().getMlss();
            List<HomeHotBean.ResultBean.PzshBean> pzsh = homeHotBean.getResult().getPzsh();
            List<HomeHotBean.ResultBean.RxxpBean> rxxp = homeHotBean.getResult().getRxxp();

            if (rxxp != null) {
                if (rxxp.size() <= 0) {
                    return;
                } else {
                    for (int i = 0; i < rxxp.size(); i++) {
                        List<HomeHotBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxp.get(i).getCommodityList();
                        homeRecycViewAdapter = new HomeRecycViewAdapter(getActivity());
                        commonHomeRviewOne.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        commonHomeRviewOne.setAdapter(homeRecycViewAdapter);
                        homeRecycViewAdapter.setoneData(commodityList, 0);
                    }
                }
            }
            if (mlss != null) {
                if (mlss.size() <= 0) {
                    return;
                } else {
                    for (int i = 0; i < mlss.size(); i++) {
                        List<HomeHotBean.ResultBean.MlssBean.CommodityListBean> commodityList = mlss.get(i).getCommodityList();
                        homeRecycViewAdapter = new HomeRecycViewAdapter(getActivity());
                        commonHomeRviewTwo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        commonHomeRviewTwo.setAdapter(homeRecycViewAdapter);
                        homeRecycViewAdapter.settwoData(commodityList, 1);
                    }
                }
            }
            if (pzsh != null) {
                if (pzsh.size() <= 0) {
                    return;
                } else {
                    for (int i = 0; i < pzsh.size(); i++) {
                        List<HomeHotBean.ResultBean.PzshBean.CommodityListBean> commodityList = pzsh.get(i).getCommodityList();
                        homeRecycViewAdapter = new HomeRecycViewAdapter(getActivity());
                        commonHomeRviewThree.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        commonHomeRviewThree.setAdapter(homeRecycViewAdapter);
                        homeRecycViewAdapter.setthreeData(commodityList, 2);
                    }
                }
            }

            initBanner();
        } else if (data instanceof HomeSerchBean) {
            HomeSerchBean homeSerchBean = (HomeSerchBean) data;
            List<HomeSerchBean.ResultBean> result = homeSerchBean.getResult();

            if (result != null) {
                serchViewAdapter = new SerchViewAdapter(getActivity());
                commonHomeRviewSerch.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                commonHomeRviewSerch.setAdapter(serchViewAdapter);
                serchViewAdapter.setData(result);

                if (result.size() == 0) {
                    scrol.setVisibility(View.GONE);
                    commonHomeRviewSerch.setVisibility(View.VISIBLE);
                    commonHomeBtnMenu.setVisibility(View.GONE);
                    commonHomeBtnReturn.setVisibility(View.VISIBLE);
                    commonHomeRviewSerch.setVisibility(View.GONE);
                    commonHomeNoneNothing.setVisibility(View.VISIBLE);
                }
            }


        } else if (data instanceof HomeBannerBean) {
            HomeBannerBean homeBannerBean = (HomeBannerBean) data;
            for (int i = 0; i < homeBannerBean.getResult().size(); i++) {
                mImgUrl.add(homeBannerBean.getResult().get(i).getImageUrl());
                initImg();
            }

        }
    }

    private void initImg() {
        xbannerHome.setData(mImgUrl, null);
        xbannerHome.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(mImgUrl.get(position)).into((ImageView) view);
            }
        });
        xbannerHome.setPageTransformer(Transformer.Default);
        xbannerHome.setPageTransformer(Transformer.Alpha);
        xbannerHome.setPageTransformer(Transformer.ZoomFade);
        xbannerHome.setPageTransformer(Transformer.ZoomCenter);
        xbannerHome.setPageTransformer(Transformer.ZoomStack);
        xbannerHome.setPageTransformer(Transformer.Stack);
        xbannerHome.setPageTransformer(Transformer.Depth);
        xbannerHome.setPageTransformer(Transformer.Zoom);
        xbannerHome.setPageChangeDuration(0);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.common_home_btn_menu, R.id.common_nav_btn_search, R.id.common_home_btn_return,
            R.id.home_btn_more_rxxp, R.id.home_btn_more_mlss, R.id.home_btn_more_pzsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_home_btn_menu:
                break;
            case R.id.common_nav_btn_search:
                String puntinname = commonNavBtnPuntinsearchname.getText().toString();
                if (puntinname.equals("")) {
                    scrol.setVisibility(View.VISIBLE);
                    commonHomeRviewSerch.setVisibility(View.GONE);
                    commonHomeBtnMenu.setVisibility(View.VISIBLE);
                    commonHomeBtnReturn.setVisibility(View.GONE);
                    return;
                } else {
                    scrol.setVisibility(View.GONE);
                    commonHomeRviewSerch.setVisibility(View.VISIBLE);
                    commonHomeBtnMenu.setVisibility(View.GONE);
                    commonHomeBtnReturn.setVisibility(View.VISIBLE);
                    iPersonter.setGetRequest(String.format(Apis.HOME_GOODS_SEARCH, puntinname), HomeSerchBean.class);
                }

                break;
            case R.id.home_btn_more_rxxp:
                break;
            case R.id.home_btn_more_mlss:
                break;
            case R.id.home_btn_more_pzsh:
                break;
            case R.id.common_home_btn_return:
                scrol.setVisibility(View.VISIBLE);
                commonHomeRviewSerch.setVisibility(View.GONE);
                commonHomeBtnMenu.setVisibility(View.VISIBLE);
                commonHomeBtnReturn.setVisibility(View.GONE);
                break;
        }
    }


}
