package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.IndentAlllsitBean;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/17
 * author:孙佳鑫(孙佳鑫)
 * function:
 */
public class ToGet_Child_Adapter extends RecyclerView.Adapter<ToGet_Child_Adapter.ViewHolder> {

    Context mContext;
    List<IndentAlllsitBean.OrderListBean.DetailListBean> mDetailListBeans;

    public ToGet_Child_Adapter(Context context) {
        mContext = context;
        mDetailListBeans=new ArrayList<>();
    }

    public void setDetailListBeans(List<IndentAlllsitBean.OrderListBean.DetailListBean> detailListBeans) {
        mDetailListBeans = detailListBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(mContext, R.layout.toget_indent_child_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String image = mDetailListBeans.get(i).getCommodityPic();
        String[] split = image.split("\\,");
        String s = split[0];
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(mContext.getResources());
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);
        GenericDraweeHierarchy build = builder.setRoundingParams(roundingParams).build();
        viewHolder.mtoget_child_item_image.setHierarchy(build);
        viewHolder.mtoget_child_item_image.setImageURI(Uri.parse(s));
        viewHolder.mtoget_child_item_title.setText(mDetailListBeans.get(i).getCommodityName());
        viewHolder.mtoget_child_item_price.setText("￥"+mDetailListBeans.get(i).getCommodityPrice()+"");
    }

    @Override
    public int getItemCount() {
        return mDetailListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mtoget_child_item_image;
        private final TextView mtoget_child_item_title;
        private final TextView mtoget_child_item_price;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            mtoget_child_item_image = itemView.findViewById(R.id.toget_child_item_image);
            mtoget_child_item_title = itemView.findViewById(R.id.toget_child_item_title);
            mtoget_child_item_price = itemView.findViewById(R.id.toget_child_item_price);
        }
    }
}
