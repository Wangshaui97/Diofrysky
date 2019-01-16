package com.bawei.www.diofrysky.view;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.fragmentadpter.FragmentAdpter;
import com.bawei.www.diofrysky.view.homefragment.HomeFragment;
import com.bawei.www.diofrysky.view.homefragment.IndentFragment;
import com.bawei.www.diofrysky.view.homefragment.MineFragment;
import com.bawei.www.diofrysky.view.homefragment.PeopleCircleFragment;
import com.bawei.www.diofrysky.view.homefragment.ShopCarFragment;
import com.bawei.www.diofrysky.view.indentfragment.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.home_pager)
    ViewPagerSlide homePager;
    @BindView(R.id.home_group)
    RadioGroup homeGroup;
    @BindView(R.id.hoem_btn_home)
    RadioButton hoemBtnHome;
    @BindView(R.id.hoem_btn_circle)
    RadioButton hoemBtnCircle;
    @BindView(R.id.hoem_btn_indent)
    RadioButton hoemBtnIndent;
    @BindView(R.id.hoem_btn_mine)
    RadioButton hoemBtnMine;
    @BindView(R.id.hoem_btn_shopcar)
    RadioButton hoemBtnShopcar;
    private List<Fragment> mlist;

    @Override
    protected int initContextView() {
        return R.layout.activity_home;
    }


    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void initData() {
        onhomePager();
    }

    private void onhomePager() {

        mlist = new ArrayList<>();
        mlist.add(new HomeFragment());
        mlist.add(new PeopleCircleFragment());
        mlist.add(new ShopCarFragment());
        mlist.add(new IndentFragment());
        mlist.add(new MineFragment());
        homePager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mlist.get(i);
            }

            @Override
            public int getCount() {
                return mlist.size();
            }
        });
        homePager.setSlide(false);

        homeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.hoem_btn_home:
                        homePager.setCurrentItem(0);
                        break;
                    case R.id.hoem_btn_circle:
                        homePager.setCurrentItem(1);
                        break;
                    case R.id.hoem_btn_shopcar:
                        homePager.setCurrentItem(2);
                        break;
                    case R.id.hoem_btn_indent:
                        homePager.setCurrentItem(3);
                        break;
                    case R.id.hoem_btn_mine:
                        homePager.setCurrentItem(4);
                        break;
                }
            }
        });
    }
}

//     ViewPager 滑动

//        homePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                switch (i) {
//                    case 0:
//                        hoemBtnHome.setChecked(true);
//                        hoemBtnCircle.setChecked(false);
//                        hoemBtnShopcar.setChecked(false);
//                        hoemBtnIndent.setChecked(false);
//                        hoemBtnMine.setChecked(false);
//                        break;
//                    case 1:
//                        hoemBtnHome.setChecked(false);
//                        hoemBtnCircle.setChecked(true);
//                        hoemBtnShopcar.setChecked(false);
//                        hoemBtnIndent.setChecked(false);
//                        hoemBtnMine.setChecked(false);
//                        break;
//                    case 2:
//                        hoemBtnHome.setChecked(false);
//                        hoemBtnCircle.setChecked(false);
//                        hoemBtnShopcar.setChecked(true);
//                        hoemBtnIndent.setChecked(false);
//                        hoemBtnMine.setChecked(false);
//                        break;
//                    case 3:
//                        hoemBtnHome.setChecked(false);
//                        hoemBtnCircle.setChecked(false);
//                        hoemBtnShopcar.setChecked(false);
//                        hoemBtnIndent.setChecked(true);
//                        hoemBtnMine.setChecked(false);
//                        break;
//                    case 4:
//                        hoemBtnHome.setChecked(false);
//                        hoemBtnCircle.setChecked(false);
//                        hoemBtnShopcar.setChecked(false);
//                        hoemBtnIndent.setChecked(false);
//                        hoemBtnMine.setChecked(true);
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
