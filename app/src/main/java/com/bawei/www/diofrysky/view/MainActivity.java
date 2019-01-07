package com.bawei.www.diofrysky.view;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.presonter.IPersonter;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IView {

    @BindView(R.id.login_putin_name)
    EditText loginPutinName;
    @BindView(R.id.login_putin_pwd)
    EditText loginPutinPwd;
    @BindView(R.id.login_putin_pwd_hind)
    ImageView loginPutinPwdHind;
    @BindView(R.id.login_putin_signup)
    TextView loginPutinSignup;
    @BindView(R.id.login_putin_success)
    Button loginPutinSuccess;
    @BindView(R.id.login_putin_rember)
    CheckBox loginPutinRember;
    private IPersonter iPersonter;
    private boolean flag = true;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;


    @Override
    protected int initContextView() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        checkPermission();
        iPersonter = new IPersonter(this);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initcheck();

    }

    private void checkPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 0);
//            } else {
//            }
//        } else {
//
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS
            };
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    }

    private void initcheck() {
        boolean aBoolean = sharedPreferences.getBoolean("ischecked", false);
        if (aBoolean) {
            loginPutinName.setText(sharedPreferences.getString("name", null));
            loginPutinPwd.setText(sharedPreferences.getString("pwd", null));
            loginPutinRember.setChecked(true);
        }
    }


    @OnClick({R.id.login_putin_pwd_hind, R.id.login_putin_signup, R.id.login_putin_success,
            R.id.login_putin_rember})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_putin_pwd_hind:
                if (flag) {
                    loginPutinPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    loginPutinPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                flag = !flag;
                break;
            case R.id.login_putin_signup:
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                break;
            case R.id.login_putin_success:
                if (loginPutinRember.isChecked()) {
                    editor.putString("name", loginPutinName.getText().toString());
                    editor.putString("pwd", loginPutinPwd.getText().toString());
                    editor.putBoolean("ischecked", true);
                    editor.commit();
                } else {
                    editor.clear();
                    editor.commit();
                }
                Boolean s = isMobileNO(loginPutinName.getText().toString());
                if (s == true) {
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", loginPutinName.getText().toString());
                    map.put("pwd", loginPutinPwd.getText().toString());
                    iPersonter.setPostRequest(Apis.LOGIN_URL, map, LoginBean.class);
                } else if (s == false) {
                    Toast.makeText(MainActivity.this, "手机格式不正确！请检查！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_putin_rember:
                if (!loginPutinRember.isChecked()) {
                    editor.clear();
                    editor.commit();
                }
                break;
        }
    }

    public static boolean isMobileNO(String mobileNums) {
        /**
         *
         *
         *判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * * 电信号段: 133,149,153,170,173,177,180,181,189
         * * @param str
         * * @return 待检测的字符串
         * */
//        String format = "[\\u4e00-\\u9fa5_a-zA-Z0-9_]";
//        String name = mName.getText().toString().trim();
//        boolean matches = Pattern.matches(format, name);  // 通过为true 验证不通过为false

        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        // "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,
        // \\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums)) {
            return false;
        } else {
            return mobileNums.matches(telRegex);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPersonter != null) {
            iPersonter.onDestich();
        }
    }

    @Override
    public void setSuccess(Object data) {
        LoginBean loginBean = (LoginBean) data;
        if (loginBean.getMessage().equals("登录成功")) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else {
            Toast.makeText(MainActivity.this, "" + loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            editor.clear();
            editor.commit();
        }

    }

}
