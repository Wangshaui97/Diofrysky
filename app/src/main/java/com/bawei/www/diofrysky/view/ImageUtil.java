package com.bawei.www.diofrysky.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ImageUtil {
        //二次采样缩小图片
        public static Bitmap scaleBitmap(String path, int width, int height){
            //创建BitmapFactory.Options
            BitmapFactory.Options options=new BitmapFactory.Options();
            //设置仅仅只解析图片边界
            options.inJustDecodeBounds=true;
            //解析出原图片的宽高
            BitmapFactory.decodeFile(path,options);
            //图片的宽高
            int photoW=options.outWidth;
            int photoH=options.outHeight;
            // 计算图像缩小比例
            int scaleFactor = Math.min(photoW / width, photoH / height);
            // 将图片文件解码为指定大小的Bitmap
            options.inJustDecodeBounds=false;
            //采样率
            options.inSampleSize=scaleFactor;
            Bitmap bitmap=BitmapFactory.decodeFile(path,options);
            return bitmap;
        }

        //压缩图片
        public static Bitmap compressImage(Bitmap bitmap, int quality, String Pathfile){
            try {
                if(bitmap.compress(Bitmap.CompressFormat.JPEG,quality,new FileOutputStream(Pathfile))){
                    bitmap=BitmapFactory.decodeFile(Pathfile);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
}
