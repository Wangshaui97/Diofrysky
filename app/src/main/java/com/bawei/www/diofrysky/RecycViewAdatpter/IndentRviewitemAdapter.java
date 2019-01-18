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
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IndentRviewitemAdapter extends RecyclerView.Adapter<IndentRviewitemAdapter.ViewHolder> {

   private Context context;
   private List<IndentAlllsitBean.OrderListBean.DetailListBean> mlist;

    public IndentRviewitemAdapter(Context context,List<IndentAlllsitBean.OrderListBean.DetailListBean> list) {
        this.context = context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public IndentRviewitemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.indent_alllist_rview_item_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndentRviewitemAdapter.ViewHolder viewHolder, int i) {
        //截取字符串
        String images = mlist.get(i).getCommodityPic();
        Pattern pen = Pattern.compile("\\,");
        String[] img = pen.split(images);
        viewHolder.alllistitemimg.setImageURI(Uri.parse(img[0]));
        viewHolder.alllistitemtitle.setText(mlist.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView alllistitemimg;
        private final TextView alllistitemtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alllistitemimg = itemView.findViewById(R.id.alllist_item_img);
            alllistitemtitle = itemView.findViewById(R.id.alllist_item_title);
        }
    }
}
