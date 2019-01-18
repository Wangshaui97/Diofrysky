package com.bawei.www.diofrysky.RecycViewAdatpter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.PeopleCirleBean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeopleCirleAdatpter extends RecyclerView.Adapter<PeopleCirleAdatpter.ViewHolder> {

    private Context context;
    private List<PeopleCirleBean.ResultBean> mlist;

    public PeopleCirleAdatpter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }

    @NonNull
    @Override
    public PeopleCirleAdatpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.peoplecircle_item_typeitemone, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleCirleAdatpter.ViewHolder viewHolder, int i) {
        viewHolder.peoplecirlehandimg.setImageURI(Uri.parse(mlist.get(i).getHeadPic()));
        viewHolder.peoplecirlehandname.setText(mlist.get(i).getNickName());

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            date = format.parse(mlist.get(i).getCreateTime());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        String  time=mlist.get(i).getCreateTime();
        long timeL =Long.parseLong(time);Date currentTime = new Date(timeL);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        viewHolder.peoplecirlehandtime.setText(dateString);

        viewHolder.peoplecirletitle.setText(mlist.get(i).getContent());
        Glide.with(context).load(mlist.get(i).getImage()).into(viewHolder.peoplecirleimg);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<PeopleCirleBean.ResultBean> beanResult) {
        this.mlist =beanResult;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView peoplecirlehandimg;
        private final TextView peoplecirlehandname, peoplecirlehandtime, peoplecirletitle;
        private final ImageView peoplecirleimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            peoplecirlehandimg = itemView.findViewById(R.id.people_cirle_handimg);
            peoplecirlehandname = itemView.findViewById(R.id.people_cirle_handname);
            peoplecirlehandtime = itemView.findViewById(R.id.people_cirle_handtime);
            peoplecirletitle = itemView.findViewById(R.id.people_cirle_title);
            peoplecirleimg = itemView.findViewById(R.id.people_cirle_img);
        }
    }
}
