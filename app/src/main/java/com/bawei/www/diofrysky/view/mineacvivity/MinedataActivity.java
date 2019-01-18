package com.bawei.www.diofrysky.view.mineacvivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.www.diofrysky.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinedataActivity extends AppCompatActivity {

    @BindView(R.id.mine_data_image)
    ImageView mineDataImage;
    @BindView(R.id.mine_data_name)
    TextView mineDataName;
    @BindView(R.id.mine_data_password)
    TextView mineDataPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minedata);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.mine_data_image, R.id.mine_data_name, R.id.mine_data_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_data_image:
                break;
            case R.id.mine_data_name:
                break;
            case R.id.mine_data_password:
                break;
        }
    }
}
