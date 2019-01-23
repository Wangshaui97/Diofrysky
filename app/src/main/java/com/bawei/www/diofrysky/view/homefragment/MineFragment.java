package com.bawei.www.diofrysky.view.homefragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.www.diofrysky.Apis;
import com.bawei.www.diofrysky.R;
import com.bawei.www.diofrysky.bean.HandimgBean;
import com.bawei.www.diofrysky.bean.LoginBean;
import com.bawei.www.diofrysky.presonter.IPersonter;
import com.bawei.www.diofrysky.view.IView;
import com.bawei.www.diofrysky.view.ImageUtil;
import com.bawei.www.diofrysky.view.mineacvivity.MineAdressActivity;
import com.bawei.www.diofrysky.view.mineacvivity.MineCiecleActivity;
import com.bawei.www.diofrysky.view.mineacvivity.MinedataActivity;
import com.bawei.www.diofrysky.view.mineacvivity.MinefoodActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends Fragment implements IView {


    @BindView(R.id.my_icon_information)
    TextView myIconInformation;
    @BindView(R.id.my_icon_circle)
    TextView myIconCircle;
    @BindView(R.id.my_icon_foot)
    TextView myIconFoot;
    @BindView(R.id.my_icon_wallet)
    TextView myIconWallet;
    @BindView(R.id.my_icon_address)
    TextView myIconAddress;
    @BindView(R.id.home_mine_userhandimg)
    SimpleDraweeView homeMineUserhandimg;
    @BindView(R.id.home_mine_username)
    TextView homeMineUsername;
    @BindView(R.id.ll)
    LinearLayout ll;
    Unbinder unbinder;
    private File file;
    private IPersonter iPersonter;
    private String nickName;
    private String headPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home_mine, null);
        unbinder = ButterKnife.bind(this, view);
        iPersonter = new IPersonter(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        homeMineUserhandimg.setImageURI(Uri.parse(headPic));
        homeMineUsername.setText(nickName);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void msg(LoginBean.ResultBean resultBean) {
        nickName = resultBean.getNickName();
        headPic = resultBean.getHeadPic();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_icon_information, R.id.my_icon_circle,
            R.id.my_icon_foot, R.id.my_icon_wallet, R.id.my_icon_address,
            R.id.home_mine_userhandimg,R.id.home_mine_username})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_icon_information:
                startActivity(new Intent(getActivity(), MinedataActivity.class));
                break;
            case R.id.my_icon_circle:
                startActivity(new Intent(getActivity(), MineCiecleActivity.class));
                break;
            case R.id.my_icon_foot:
                startActivity(new Intent(getActivity(), MinefoodActivity.class));
                break;
            case R.id.my_icon_wallet:
                break;
            case R.id.my_icon_address:
                startActivity(new Intent(getActivity(),MineAdressActivity.class));
                break;
            case R.id.home_mine_userhandimg:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
                break;
            case R.id.home_mine_username:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请输入");
                final View view1 = View.inflate(getActivity(), R.layout.alert_dialog, null);
                builder.setView(view1);
                builder.setMessage("请输入你要修改的名称");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = view1.findViewById(R.id.username);
                        if(!editText.getText().toString().equals("")){
                            Map<String,String> map = new HashMap<>();
                            map.put("nickName",editText.getText().toString());
                            iPersonter.setPutRequest(Apis.changeName,map,LoginBean.class);
                            homeMineUsername.setText(editText.getText().toString());
                        }

                    }
                });
                builder.show();

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //获取图片路径
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            String filePath = getFilePath(null, requestCode, data);
            /**
             * 这里是用的上传头像
             */
            Map<String, Object> map = new HashMap<>();
            List<Object> list = new ArrayList<>();
            list.add(filePath);
            iPersonter.getrequestimgtitle(Apis.PUSH_HANDIMG, map, list, HandimgBean.class);
        }
    }
//
//        if(requestCode==0&&resultCode==RESULT_OK){
//            Bitmap bitmap=data.getParcelableExtra("data");
//            //将图片保存为文件
//            File file = new File(createImageFilePath());
//            try {
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            //缩放减小分辨率影响图片文件大小，加载到内存中占用的大小
//            // 压缩降低图片质量 不影响分辨率， 影响图片文件大小，加载到内存中占用的大小不变
//            //缩放（调用ImageUtil中的缩放方法）
//            Bitmap bitmap1 = ImageUtil.scaleBitmap(file.getAbsolutePath(), 100, 100);
//            //压缩（调用ImageUtil中的压缩方法）
//            ImageUtil.compressImage(bitmap1, 70, file.getAbsolutePath());
//            //加载最终图片结果
//            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//            //设置图片
//           // personalImage.setImageBitmap(bitmap);
//
//            String filePath = getFilePath(null,requestCode,data);
//            /**
//             * 这里是用的上传头像
//             */
//            Map<String, Object> map = new HashMap<>();
//            List<Object> list =new ArrayList<>();
//            list.add(filePath);
//            iPersonter.getrequestimgtitle(Apis.PUSH_HANDIMG,map,list,HandimgBean.class);
//        }




    private String createImageFilePath() {
        //裁剪之后的图片（每裁剪一次会覆盖之前的裁剪图片）

        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/t.png";

    }

    public String getFilePath(String fileName, int requestCode, Intent data) {
        if (requestCode == 1) {
            return fileName;
        } else if (requestCode == 0) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor
                    .getString(actual_image_column_index);
            // 4.0以上平台会自动关闭cursor,所以加上版本判断,OK
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH){
                actualimagecursor.close();
            }
            return img_path;
        }
        return null;
    }

    @Override
    public void setSuccess(Object data) {
       if(data instanceof HandimgBean){
           HandimgBean handimgBean = (HandimgBean) data;
           Toast.makeText(getActivity(),""+handimgBean.getMessage(),Toast.LENGTH_SHORT).show();
//        homeMineUserhandimg.setImageURI(Uri.parse(handimgBean.getHeadPath()));
           String headPath = handimgBean.getHeadPath();
           DraweeController controller = Fresco.newDraweeControllerBuilder()
                   .setUri(headPath)
                   .build();
           homeMineUserhandimg.setController(controller);
       }else if(data instanceof  LoginBean){
           LoginBean loginBean = (LoginBean) data;
           Toast.makeText(getActivity(),""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
       }
    }
}
