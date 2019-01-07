package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.EventBusMassager;
import com.bawei.www.diofrysky.bean.HomeHotBean;
import com.bawei.www.diofrysky.view.DetailsActivity;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class HomeRecycViewAdapter extends RecyclerView.Adapter<HomeRecycViewAdapter.ViewHolder> {

    private Context context;
    private List<HomeHotBean.ResultBean.MlssBean.CommodityListBean> mlssBeans;
    private List<HomeHotBean.ResultBean.PzshBean.CommodityListBean> pzshBeans;
    private List<HomeHotBean.ResultBean.RxxpBean.CommodityListBean> rxxpBeans;
    private int mindex;

    public HomeRecycViewAdapter(Context context) {
        this.context = context;
        mlssBeans = new ArrayList<>();
        pzshBeans = new ArrayList<>();
        rxxpBeans = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeRecycViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mindex == 0) {
            View view = View.inflate(context, R.layout.common_home_rview_one_item, null);
            return new ViewHolder(view);
        } else if (mindex == 1) {
            View view = View.inflate(context, R.layout.common_home_rview_two_item, null);
            return new ViewHolder(view);
        } else if (mindex == 2) {
            View view = View.inflate(context, R.layout.common_home_rview_three_item, null);
            return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecycViewAdapter.ViewHolder viewHolder, final int i) {
        if (mindex == 0) {
            viewHolder.one_title.setText(rxxpBeans.get(i).getCommodityName());
            viewHolder.one_price.setText("￥" + rxxpBeans.get(i).getPrice());
            GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);
            GenericDraweeHierarchy build = builder.setRoundingParams(roundingParams).build();
            viewHolder.one_img.setHierarchy(build);
            viewHolder.one_img.setImageURI(Uri.parse(rxxpBeans.get(i).getMasterPic()));

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int commodityId = rxxpBeans.get(i).getCommodityId();
                    EventBus.getDefault().postSticky(new EventBusMassager(commodityId));
                    context.startActivity(new Intent(context,DetailsActivity.class));
                }
            });
            return;
        }
        if (mindex == 1) {
            viewHolder.two_title.setText(mlssBeans.get(i).getCommodityName());
            viewHolder.two_price.setText("￥" + mlssBeans.get(i).getPrice());
            GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);
            GenericDraweeHierarchy build = builder.setRoundingParams(roundingParams).build();
            viewHolder.two_img.setHierarchy(build);
            viewHolder.two_img.setImageURI(Uri.parse(mlssBeans.get(i).getMasterPic()));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int commodityId = mlssBeans.get(i).getCommodityId();
                    EventBus.getDefault().postSticky(new EventBusMassager(commodityId));
                    context.startActivity(new Intent(context,DetailsActivity.class));
                }
            });
            return;
        }
        if (mindex == 2) {
            viewHolder.three_title.setText(pzshBeans.get(i).getCommodityName());
            viewHolder.three_price.setText("￥" + pzshBeans.get(i).getPrice());
            GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);
            GenericDraweeHierarchy build = builder.setRoundingParams(roundingParams).build();
            viewHolder.three_img.setHierarchy(build);
            viewHolder.three_img.setImageURI(Uri.parse(pzshBeans.get(i).getMasterPic()));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int commodityId = pzshBeans.get(i).getCommodityId();
                    EventBus.getDefault().postSticky(new EventBusMassager(commodityId));
                    context.startActivity(new Intent(context,DetailsActivity.class));
                }
            });
            return;
        }
    }

    @Override
    public int getItemCount() {
        if (mindex == 0) {
            return 3;
        } else if (mindex == 1) {
            return 2;
        } else if (mindex == 2) {
            return 4;
        }
        return 0;
    }

    public void setoneData(List<HomeHotBean.ResultBean.RxxpBean.CommodityListBean> rxxp, int index) {
        this.rxxpBeans = rxxp;
        this.mindex = index;
        notifyDataSetChanged();
    }

    public void settwoData(List<HomeHotBean.ResultBean.MlssBean.CommodityListBean> mlss, int index) {
        this.mlssBeans = mlss;
        notifyDataSetChanged();
        this.mindex = index;
    }

    public void setthreeData(List<HomeHotBean.ResultBean.PzshBean.CommodityListBean> pzsh, int index) {
        this.pzshBeans = pzsh;
        notifyDataSetChanged();
        this.mindex = index;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView one_title, two_title, three_title;
        TextView one_price, two_price, three_price;
        SimpleDraweeView one_img, two_img, three_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (mindex == 0) {
                one_title = itemView.findViewById(R.id.common_home_rview_one_title);
                one_price = itemView.findViewById(R.id.common_home_rview_one_privce);
                one_img = itemView.findViewById(R.id.common_home_rview_one_img);
            } else if (mindex == 1) {
                two_title = itemView.findViewById(R.id.common_home_rview_two_title);
                two_price = itemView.findViewById(R.id.common_home_rview_two_privce);
                two_img = itemView.findViewById(R.id.common_home_rview_two_img);
            } else if (mindex == 2) {
                three_title = itemView.findViewById(R.id.common_home_rview_three_title);
                three_price = itemView.findViewById(R.id.common_home_rview_three_privce);
                three_img = itemView.findViewById(R.id.common_home_rview_three_img);
            }
        }
    }

}
