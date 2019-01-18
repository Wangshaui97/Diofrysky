package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.ShopCarBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class PayIndentAdapter extends RecyclerView.Adapter<PayIndentAdapter.ViewHolder> {
    private Context context;
    private List<ShopCarBean.ResultBean> mlist;

    public PayIndentAdapter(Context context) {
        this.context = context;
        mlist =new ArrayList<>();
    }

    @NonNull
    @Override
    public PayIndentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.payindent_rview_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PayIndentAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemshopcartitle.setText(mlist.get(i).getCommodityName());
        viewHolder.itemshopcarprice.setText("￥:" + mlist.get(i).getPrice());
        viewHolder.itemshopcarnum.setText(mlist.get(i).getCount() + "");
        viewHolder.itemshopcarimg.setImageURI(Uri.parse(mlist.get(i).getPic()));
    }

    public void setData(List<ShopCarBean.ResultBean> result) {
        this.mlist = result;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemshopcartitle, itemshopcarprice, itemshopcarnum;
        private final SimpleDraweeView itemshopcarimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemshopcartitle = itemView.findViewById(R.id.payindent_rview_title);
            itemshopcarprice = itemView.findViewById(R.id.payindent_rview_privce);
            itemshopcarnum = itemView.findViewById(R.id.payindent_rview_num);
            itemshopcarimg = itemView.findViewById(R.id.payindent_rview_img);
        }
    }
}
