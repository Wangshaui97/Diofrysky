package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.EventBusMassager;
import com.bawei.www.diofrysky.bean.HomeSerchBean;
import com.bawei.www.diofrysky.view.DetailsActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SerchViewAdapter extends RecyclerView.Adapter<SerchViewAdapter.ViewHolder> {
    private Context context;
    private List<HomeSerchBean.ResultBean> mlist;

    public SerchViewAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }

    @NonNull
    @Override
    public SerchViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.common_home_rview_serch_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SerchViewAdapter.ViewHolder viewHolder, final int i) {

         viewHolder.itemtitle.setText(mlist.get(i).getCommodityName());
         viewHolder.itemprice.setText("￥"+mlist.get(i).getPrice()+"");
         viewHolder.itemimg.setImageURI(Uri.parse(mlist.get(i).getMasterPic()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId = mlist.get(i).getCommodityId();
                EventBus.getDefault().postSticky(new EventBusMassager(commodityId));
                context.startActivity(new Intent(context,DetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<HomeSerchBean.ResultBean> result) {
        this.mlist=result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemtitle,itemprice;
        private final SimpleDraweeView itemimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemtitle = itemView.findViewById(R.id.common_home_rview_serch_item_title);
            itemprice = itemView.findViewById(R.id.common_home_rview_serch_item_privce);
            itemimg = itemView.findViewById(R.id.common_home_rview_serch_item_img);
        }
    }
}
