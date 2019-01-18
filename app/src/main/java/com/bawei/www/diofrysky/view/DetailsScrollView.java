package com.bawei.www.diofrysky.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class DetailsScrollView extends ScrollView {
    public DetailsScrollView(Context context) {
        this(context,null);
    }

    public DetailsScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DetailsScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener !=null){
            mScrollViewListener.onScrollChange(this,l,t,oldl,oldt);
        }
    }


    public interface ScrollViewListener{
        void onScrollChange(DetailsScrollView scrollView, int l, int t, int oldl, int oldt);
    }

    private ScrollViewListener  mScrollViewListener;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }
}
