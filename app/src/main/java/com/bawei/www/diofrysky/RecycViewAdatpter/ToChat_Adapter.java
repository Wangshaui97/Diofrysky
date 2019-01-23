package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.IndentAlllsitBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/17
 * author:孙佳鑫(孙佳鑫)
 * function:
 */
public class ToChat_Adapter extends XRecyclerView.Adapter<ToChat_Adapter.ViewHolder> {
    Context mContext;
    List<IndentAlllsitBean.OrderListBean> mOrderListBeans;
    private ToChat_Child_Adapter mToChat_child_adapter;
    private List<IndentAlllsitBean.OrderListBean.DetailListBean> mDetailList;

    public ToChat_Adapter(Context context) {
        mContext = context;
        mOrderListBeans=new ArrayList<>();
    }
    public void setOrderListBeans(List<IndentAlllsitBean.OrderListBean> orderListBeans) {
        mOrderListBeans = orderListBeans;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.tochatindent_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.mtochat_item_num.setText(mOrderListBeans.get(i).getOrderId()+"");

        mToChat_child_adapter = new ToChat_Child_Adapter(mContext);
        mDetailList = mOrderListBeans.get(i).getDetailList();
        mToChat_child_adapter.setDetailListBeans(mDetailList);
        viewHolder.mtochat_item_recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        viewHolder.mtochat_item_recyclerview.setAdapter(mToChat_child_adapter);
    }

    @Override
    public int getItemCount() {
        return mOrderListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mtochat_item_num;
        private final RecyclerView mtochat_item_recyclerview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtochat_item_num = itemView.findViewById(R.id.tochat_item_num);
            mtochat_item_recyclerview = itemView.findViewById(R.id.tochat_item_recyclerview);
        }
    }


}
