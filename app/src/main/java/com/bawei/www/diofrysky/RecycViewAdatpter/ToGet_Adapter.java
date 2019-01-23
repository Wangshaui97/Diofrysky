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
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/17
 * author:孙佳鑫(孙佳鑫)
 * function:
 */
public class ToGet_Adapter extends XRecyclerView.Adapter<ToGet_Adapter.ViewHolder> {
    Context mContext;
    List<IndentAlllsitBean.OrderListBean> mOrderListBeans;
    private ToGet_Child_Adapter mToGet_child_adapter;
    private List<IndentAlllsitBean.OrderListBean.DetailListBean> mDetailListBeans;

    public ToGet_Adapter(Context context) {
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
        View view = View.inflate(mContext, R.layout.togetindent_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mtoget_item_num.setText(mOrderListBeans.get(i).getOrderId()+"");
        viewHolder.mtoget_item_kuaidi.setText("派件公司:"+mOrderListBeans.get(i).getExpressCompName());
        viewHolder.mtoget_item_bianhao.setText("快递编号:"+mOrderListBeans.get(i).getExpressSn());


        mToGet_child_adapter = new ToGet_Child_Adapter(mContext);
        mDetailListBeans = mOrderListBeans.get(i).getDetailList();
        mToGet_child_adapter.setDetailListBeans(mDetailListBeans);

        viewHolder.mtoget_item_recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        viewHolder.mtoget_item_recyclerview.setAdapter(mToGet_child_adapter);

        viewHolder.mtoget_item_shouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnclickId!=null){
                    mOnclickId.onSuccess(mOrderListBeans.get(i).getOrderId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrderListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mtoget_item_num;
        private final TextView mtoget_item_time;
        private final TextView mtoget_item_kuaidi;
        private final TextView mtoget_item_bianhao;
        private final Button mtoget_item_shouhuo;
        private final RecyclerView mtoget_item_recyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtoget_item_num = itemView.findViewById(R.id.toget_item_num);
            mtoget_item_time = itemView.findViewById(R.id.toget_item_time);
            mtoget_item_kuaidi = itemView.findViewById(R.id.toget_item_kuaidi);
            mtoget_item_bianhao = itemView.findViewById(R.id.toget_item_bianhao);
            mtoget_item_shouhuo = itemView.findViewById(R.id.toget_item_shouhuo);
            mtoget_item_recyclerview = itemView.findViewById(R.id.toget_item_recyclerview);
        }
    }

    public interface OnclickId{
        void onSuccess(String orderId);
    }

    private OnclickId mOnclickId;

    public void setOnclickId(OnclickId onclickId){
        mOnclickId=onclickId;
    }

}
