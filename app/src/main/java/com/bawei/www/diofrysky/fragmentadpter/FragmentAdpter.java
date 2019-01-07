package com.bawei.www.diofrysky.fragmentadpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.www.diofrysky.view.homefragment.HomeFragment;
import com.bawei.www.diofrysky.view.homefragment.IndentFragment;
import com.bawei.www.diofrysky.view.homefragment.MineFragment;
import com.bawei.www.diofrysky.view.homefragment.PeopleCircleFragment;
import com.bawei.www.diofrysky.view.homefragment.ShopCarFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdpter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> mlist;

    public FragmentAdpter(FragmentManager fm ,Context context,List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();
            case 1:
                return new PeopleCircleFragment();
            case 2:
                return new ShopCarFragment();
            case 3:
                return new IndentFragment();
            case 4:
                return new MineFragment();
                default:
                    return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
