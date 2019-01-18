package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.IndentAlllsitBean;
import com.bawei.www.diofrysky.view.PayIndentActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class IndentRviewAdapter extends RecyclerView.Adapter<IndentRviewAdapter.ViewHolder> {
   private Context context;
   private List<IndentAlllsitBean.OrderListBean> mlist;
    private IndentRviewitemAdapter indentRviewitemAdapter;

    public IndentRviewAdapter(Context context) {
        this.context = context;
        mlist =new ArrayList<>();
    }

    @NonNull
    @Override
    public IndentRviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.indent_alllist_rview_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndentRviewAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.alllistitemorderId.setText("订单号为："+mlist.get(i).getOrderId());
        viewHolder.alllistitemprice.setText(""+mlist.get(i).getPayAmount());

        indentRviewitemAdapter = new IndentRviewitemAdapter(context,mlist.get(i).getDetailList());
        viewHolder.alllistitemrview.setAdapter(indentRviewitemAdapter);
        viewHolder.alllistitemrview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

       viewHolder.indentitemdel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                if(listener!=null){
                    listener.setSuccess(mlist.get(i).getOrderId());
                }
           }
       });

       viewHolder.indentitempay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                if(paylistener!=null){
                    paylistener.setSuccess(mlist.get(i).getOrderId());
                }
           }
       });

    }
    private CheckListener listener;

    public interface CheckListener {
        void setSuccess(String orderId);
    }

    public void listener(CheckListener Clicklistenr) {
        this.listener = Clicklistenr;
    }


    private PayCheckListener paylistener;

    public interface PayCheckListener {
        void setSuccess(String orderId);
    }

    public void listener(PayCheckListener Clicklistenr) {
        this.paylistener = Clicklistenr;
    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<IndentAlllsitBean.OrderListBean> orderList) {
        this.mlist = orderList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView alllistitemorderId;
        private final RecyclerView alllistitemrview;
        private final TextView alllistitemprice;
        private final Button indentitempay,indentitemdel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alllistitemorderId = itemView.findViewById(R.id.alllist_item_orderId);
            alllistitemrview = itemView.findViewById(R.id.alllist_item_rview);
            alllistitemprice = itemView.findViewById(R.id.alllist_item_price);
            indentitempay = itemView.findViewById(R.id.indent_item_pay);
            indentitemdel= itemView.findViewById(R.id.indent_item_del);
        }
    }
}
