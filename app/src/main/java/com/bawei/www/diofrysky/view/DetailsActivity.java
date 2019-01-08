package com.bawei.www.diofrysky.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.RecycViewAdatpter.DetailsBannerAdapter;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.bean.DetailsBean;
import com.bawei.www.diofrysky.bean.EventBusMassager;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity implements IView {


    @BindView(R.id.details_viewpager_show)
    ViewPager detailsViewpagerShow;
    @BindView(R.id.details_textview_shownum)
    TextView detailsTextviewShownum;
    @BindView(R.id.details_textview_sprice)
    TextView detailsTextviewSprice;
    @BindView(R.id.details_textview_sold)
    TextView detailsTextviewSold;
    @BindView(R.id.details_textview_title)
    TextView detailsTextviewTitle;
    @BindView(R.id.details_textview_Weight)
    TextView detailsTextviewWeight;
    @BindView(R.id.details_Image_details)
    SimpleDraweeView detailsImageDetails;
    @BindView(R.id.details_textview_describe)
    TextView detailsTextviewDescribe;
    @BindView(R.id.details_Image_describe)
    SimpleDraweeView detailsImageDescribe;
    @BindView(R.id.details_recview_comments)
    RecyclerView detailsRecviewComments;
    @BindView(R.id.details_textview_comments)
    TextView detailsTextviewComments;
    @BindView(R.id.details_scroll_changecolor)
    DetailsScrollView detailsScrollChangecolor;
    @BindView(R.id.details_image_return)
    ImageView detailsImageReturn;
    @BindView(R.id.details_text_goodsT)
    TextView detailsTextGoodsT;
    @BindView(R.id.details_text_detailsT)
    TextView detailsTextDetailsT;
    @BindView(R.id.details_text_commentsT)
    TextView detailsTextCommentsT;
    @BindView(R.id.details_text_goods)
    TextView detailsTextGoods;
    @BindView(R.id.details_text_details)
    TextView detailsTextDetails;
    @BindView(R.id.details_text_comments)
    TextView detailsTextComments;
    @BindView(R.id.details_relative_changer)
    RelativeLayout detailsRelativeChanger;
    @BindView(R.id.details_relat_changecolor)
    RelativeLayout detailsRelatChangecolor;
    @BindView(R.id.details_relative_addshoppingcar)
    RelativeLayout detailsRelativeAddshoppingcar;
    private int index;
    private int mCount;
    private int commodityId;
    private IPersonter mIPresenter;

    @Override
    protected int initContextView() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

        mIPresenter = new IPersonter(this);
       mIPresenter.setGetRequest(String.format(Apis.HOME_GOODS_DETAILS,index),DetailsBean.class);

       initColor();
    }

    private void initColor() {
        detailsScrollChangecolor.setScrollViewListener(new DetailsScrollView.ScrollViewListener() {
            @Override
            public void onScrollChange(DetailsScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    detailsRelativeChanger.setVisibility(View.GONE);
                    detailsRelatChangecolor.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    detailsRelativeChanger.setVisibility(View.VISIBLE);
                    float scale = (float) t / 200;
                    float alpha = 255 * scale;
                    detailsRelatChangecolor.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                }
                if (0 < t && t < 700) {
                    detailsTextGoods.setBackgroundColor(Color.RED);
                    detailsTextDetails.setBackgroundColor(Color.WHITE);
                    detailsTextComments.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 1500) {
                    detailsTextGoods.setBackgroundColor(Color.WHITE);
                    detailsTextDetails.setBackgroundColor(Color.RED);
                    detailsTextComments.setBackgroundColor(Color.WHITE);
                } else if (t > 1500) {
                    detailsTextGoods.setBackgroundColor(Color.WHITE);
                    detailsTextDetails.setBackgroundColor(Color.WHITE);
                    detailsTextComments.setBackgroundColor(Color.RED);
                }
            }
        });

        detailsViewpagerShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                detailsTextviewShownum.setText((i + 1) + "/" + mCount);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void massage(EventBusMassager eventBusMassager) {
        index = eventBusMassager.getIndex();
        Toast.makeText(DetailsActivity.this, "" + index, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setSuccess(Object data) {
        if(data instanceof DetailsBean){
            DetailsBean detailsBean= (DetailsBean) data;
            detailsTextviewSprice.setText("￥" + detailsBean.getResult().getPrice());
            detailsTextviewSold.setText("已售" + detailsBean.getResult().getSaleNum() + "件");
            detailsTextviewTitle.setText(detailsBean.getResult().getCommodityName());
            detailsTextviewWeight.setText(detailsBean.getResult().getWeight() + "kg");
            detailsTextviewDescribe.setText(detailsBean.getResult().getDescribe());

            String Pictures = detailsBean.getResult().getPicture().trim();
            String[] split = Pictures.split(",");

            detailsImageDetails.setImageURI(split[0]);
            detailsImageDescribe.setImageURI(split[1]);

            DetailsBannerAdapter adapter = new DetailsBannerAdapter(this, split);
            mCount = adapter.getCount();
            detailsViewpagerShow.setAdapter(adapter);

            commodityId = detailsBean.getResult().getCommodityId();
        }else if(data instanceof LoginBean){
            LoginBean loginBean = (LoginBean) data;
            Toast.makeText(DetailsActivity.this, "" + loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }



    @OnClick({R.id.details_image_return,R.id.details_relative_addshoppingcar, R.id.details_text_goodsT, R.id.details_text_detailsT, R.id.details_text_commentsT})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.details_image_return:
                finish();
                break;
            case R.id.details_text_goodsT:
                detailsRelatChangecolor.setScrollY(0);
                break;
            case R.id.details_text_detailsT:
                detailsRelatChangecolor.setScrollY(702);
                break;
            case R.id.details_text_commentsT:
                detailsRelatChangecolor.setScrollY(1501);
                break;
            case R.id.details_relative_addshoppingcar:
                Map<String,String> map = new HashMap<>();
               // map.put("data","[{\"commodityId\":"+commodityId+",\"count\":1}]");
                map.put("data","[{\"commodityId\":"+commodityId+",\"count\":1}]");
                mIPresenter.setPutRequest(Apis.ADD_SHOPCAR,map,LoginBean.class);
                break;
        }
    }


}
