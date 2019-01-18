package com.bawei.www.diofrysky.view.mineacvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineAdressActivity extends BaseActivity {
    @BindView(R.id.address_text_finish)
    TextView addressTextFinish;
    @BindView(R.id.address_recy)
    RecyclerView addressRecy;
    @BindView(R.id.address_button_add)
    Button addressButtonAdd;
    @Override
    protected int initContextView() {
        return R.layout.activity_mine_adress;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.address_text_finish, R.id.address_button_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_text_finish:
                finish();
                break;
            case R.id.address_button_add:
                startActivity(new Intent(MineAdressActivity.this,NewAddressActivity.class));
                break;
        }
    }
}
