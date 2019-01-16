package com.bawei.www.diofrysky.view.homefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.view.indentfragment.AlllsitFragment;
import com.bawei.www.diofrysky.view.indentfragment.FinishFragment;
import com.bawei.www.diofrysky.view.indentfragment.NopayFragment;
import com.bawei.www.diofrysky.view.indentfragment.NothingFragment;
import com.bawei.www.diofrysky.view.indentfragment.TalckFragment;
import com.bawei.www.diofrysky.view.indentfragment.ViewPagerSlide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class IndentFragment extends Fragment {


    @BindView(R.id.indent_btn_all)
    SimpleDraweeView indentBtnAll;
    @BindView(R.id.indent_btn_nopay)
    SimpleDraweeView indentBtnNopay;
    @BindView(R.id.indent_btn_nothing)
    SimpleDraweeView indentBtnNothing;
    @BindView(R.id.indent_btn_talck)
    SimpleDraweeView indentBtnTalck;
    @BindView(R.id.indent_btn_finish)
    SimpleDraweeView indentBtnFinish;
    @BindView(R.id.indent_viewpager)
    ViewPagerSlide indentViewpager;
    Unbinder unbinder;
    private List<Fragment> mlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home_indent, null);
        unbinder = ButterKnife.bind(this, view);

        onhomePager();
        return view;
    }

    private void onhomePager() {
        mlist = new ArrayList<>();
        mlist.add(new AlllsitFragment());
        mlist.add(new NopayFragment());
        mlist.add(new NothingFragment());
        mlist.add(new TalckFragment());
        mlist.add(new FinishFragment());

//        indentViewpager.setSlide(false);

        indentViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mlist.get(i);
            }

            @Override
            public int getCount() {
                return mlist.size();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.indent_btn_all, R.id.indent_btn_nopay, R.id.indent_btn_nothing, R.id.indent_btn_talck, R.id.indent_btn_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.indent_btn_all:
                indentViewpager.setCurrentItem(0);
                break;
            case R.id.indent_btn_nopay:
                indentViewpager.setCurrentItem(1);
                break;
            case R.id.indent_btn_nothing:
                indentViewpager.setCurrentItem(2);
                break;
            case R.id.indent_btn_talck:
                indentViewpager.setCurrentItem(3);
                break;
            case R.id.indent_btn_finish:
                indentViewpager.setCurrentItem(4);
                break;
        }
    }
}
