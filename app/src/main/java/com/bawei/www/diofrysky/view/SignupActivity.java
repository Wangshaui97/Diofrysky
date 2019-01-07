package com.bawei.www.diofrysky.view;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.base.BaseActivity;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.presonter.IPersonter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements IView {

    @BindView(R.id.signup_putin_name)
    EditText signupPutinName;
    @BindView(R.id.signup_putin_yanzheng)
    EditText signupPutinYanzheng;
    @BindView(R.id.signup_putin_pwd_huoqu)
    TextView signupPutinPwdHuoqu;
    @BindView(R.id.signup_putin_pwd)
    EditText signupPutinPwd;
    @BindView(R.id.signup_putin_pwd_hind)
    ImageView signupPutinPwdHind;
    @BindView(R.id.signup_putin_signup)
    TextView signupPutinSignup;
    @BindView(R.id.signup_putin_success)
    Button signupPutinSuccess;
    private Boolean flag = false;
    private IPersonter iPersonter;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        iPersonter = new IPersonter(this);
    }

    @Override
    protected int initContextView() {
        return R.layout.activity_signup;
    }


    @Override
    public void setSuccess(Object data) {
        LoginBean loginBean = (LoginBean) data;
        if(loginBean.getMessage().equals("注册成功")){
            Toast.makeText(SignupActivity.this,loginBean.getMessage()+",您可以登录了",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignupActivity.this,MainActivity.class));
        }else {
            Toast.makeText(SignupActivity.this,""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.signup_putin_pwd_hind, R.id.signup_putin_signup, R.id.signup_putin_success})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.signup_putin_pwd_hind:
                if (flag) {
                    signupPutinPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    signupPutinPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                flag = !flag;
                break;
            case R.id.signup_putin_signup:
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                break;
            case R.id.signup_putin_success:
                Boolean s = isMobileNO(signupPutinName.getText().toString());
                if (s == true) {
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", signupPutinName.getText().toString());
                    map.put("pwd", signupPutinPwd.getText().toString());
                    iPersonter.setPostRequest(Apis.SIGNUP_URL, map,LoginBean.class);
                } else if (s == false) {
                    Toast.makeText(SignupActivity.this, "手机格式不正确！请检查！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPersonter != null) {
            iPersonter.onDestich();
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
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        // "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,
        // \\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums)) {
            return false;
        } else {
            return mobileNums.matches(telRegex);
        }
    }
}
