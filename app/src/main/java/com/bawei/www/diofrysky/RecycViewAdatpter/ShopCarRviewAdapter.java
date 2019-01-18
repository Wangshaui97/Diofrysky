package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.ShopCarBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ShopCarRviewAdapter extends RecyclerView.Adapter<ShopCarRviewAdapter.ViewHolder> {

    private Context context;
    private List<ShopCarBean.ResultBean> mlist;

    public ShopCarRviewAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }

    @NonNull
    @Override
    public ShopCarRviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.shopcar_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopCarRviewAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.itemshopcartitle.setText(mlist.get(i).getCommodityName());
        viewHolder.itemshopcarprice.setText("￥:" + mlist.get(i).getPrice());
        viewHolder.itemshopcarnum.setText(mlist.get(i).getCount() + "");
        viewHolder.itemshopcarimg.setImageURI(Uri.parse(mlist.get(i).getPic()));

        mlist.get(i).setIschecked(mlist.get(i).isIschecked());

        viewHolder.itemshopcarcheck.setChecked(mlist.get(i).isIschecked());

        viewHolder.itemshopcarcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlist.get(i).setIschecked(viewHolder.itemshopcarcheck.isChecked());
                if (listener != null) {
                    listener.setSuccess(mlist);
                }
            }
        });


        viewHolder.itemshopcarjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlist.get(i).setCount(mlist.get(i).getCount() + 1);
                viewHolder.itemshopcarnum.setText(mlist.get(i).getCount() + "");
                if (listener != null) {
                    listener.setSuccess(mlist);
                }
            }
        });

        viewHolder.itemshopcarjian.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlist.get(i).getCount() > 1) {
                    mlist.get(i).setCount(mlist.get(i).getCount() - 1);
                    viewHolder.itemshopcarnum.setText(mlist.get(i).getCount() + "");
                    if (listener != null) {
                        listener.setSuccess(mlist);
                    }
                }
            }
        });

    }

    public void setChecked(boolean checked) {
        for (int i = 0; i < mlist.size(); i++) {
            mlist.get(i).setIschecked(checked);
        }
        notifyDataSetChanged();
    }

    private CheckListener listener;

    public interface CheckListener {
        void setSuccess(List<ShopCarBean.ResultBean> list);
    }

    public void listener(CheckListener Clicklistenr) {
        this.listener = Clicklistenr;
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<ShopCarBean.ResultBean> result) {
        this.mlist = result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemshopcartitle, itemshopcarprice, itemshopcarnum;
        private final SimpleDraweeView itemshopcarimg;
        private final Button itemshopcarjia;
        private final Button itemshopcarjian;
        private final CheckBox itemshopcarcheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemshopcartitle = itemView.findViewById(R.id.item_shopcar_title);
            itemshopcarprice = itemView.findViewById(R.id.item_shopcar_privce);
            itemshopcarnum = itemView.findViewById(R.id.item_shopcar_num);
            itemshopcarimg = itemView.findViewById(R.id.item_shopcar_img);
            itemshopcarjia = itemView.findViewById(R.id.item_shopcar_jia);
            itemshopcarjian = itemView.findViewById(R.id.item_shopcar_jian);
            itemshopcarcheck = itemView.findViewById(R.id.item_shopcar_checked);
        }
    }
}
