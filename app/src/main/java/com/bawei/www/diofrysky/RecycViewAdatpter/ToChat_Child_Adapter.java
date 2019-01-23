package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class ToChat_Child_Adapter extends RecyclerView.Adapter<ToChat_Child_Adapter.ViewHolder> {

    Context mContext;
    List<IndentAlllsitBean.OrderListBean.DetailListBean> mDetailListBeans;

    public ToChat_Child_Adapter(Context context) {
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
        View inflate = View.inflate(mContext, R.layout.tochat_indent_child_item, null);
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
        viewHolder.mtochat_child_item_image.setHierarchy(build);
        viewHolder.mtochat_child_item_image.setImageURI(Uri.parse(s));
        viewHolder.mtochat_child_item_title.setText(mDetailListBeans.get(i).getCommodityName());
        viewHolder.mtochat_child_item_price.setText("￥"+mDetailListBeans.get(i).getCommodityPrice()+"");

        viewHolder.mtochat_child_item_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mContext.startActivity(new Intent(mContext,ChatActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDetailListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mtochat_child_item_image;
        private final TextView mtochat_child_item_title;
        private final TextView mtochat_child_item_price;
        private final Button mtochat_child_item_chat;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            mtochat_child_item_image = itemView.findViewById(R.id.tochat_child_item_image);
            mtochat_child_item_title = itemView.findViewById(R.id.tochat_child_item_title);
            mtochat_child_item_price = itemView.findViewById(R.id.tochat_child_item_price);
            mtochat_child_item_chat = itemView.findViewById(R.id.tochat_child_item_chat);
        }
    }
}
