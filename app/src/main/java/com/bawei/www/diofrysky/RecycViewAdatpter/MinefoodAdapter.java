package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.FoodsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MinefoodAdapter extends RecyclerView.Adapter<MinefoodAdapter.ViewHolder> {

    private Context context;
    private List<FoodsBean.ResultBean> mlist;

    public MinefoodAdapter(Context context) {
        this.context = context;
        mlist=new ArrayList<>();
    }

    @NonNull
    @Override
    public MinefoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.mine_foods_rview_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MinefoodAdapter.ViewHolder viewHolder, int i) {
        String  time=mlist.get(i).getBrowseTime();
        long timeL =Long.parseLong(time);Date currentTime = new Date(timeL);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        viewHolder.itemtime.setText(dateString);
        viewHolder.itemtitle.setText(mlist.get(i).getCommodityName());
        viewHolder.itemprice.setText(mlist.get(i).getPrice()+"");
        viewHolder.itemimg.setImageURI(Uri.parse(mlist.get(i).getMasterPic()));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<FoodsBean.ResultBean> result) {
        this.mlist=result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView itemimg;
        private final TextView itemtitle,itemtime,itemprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemimg = itemView.findViewById(R.id.mine_foods_item_img);
            itemtitle = itemView.findViewById(R.id.mine_foods_item_title);
            itemtime = itemView.findViewById(R.id.mine_foods_item_time);
            itemprice = itemView.findViewById(R.id.mine_foods_item_privce);
        }
    }
}
