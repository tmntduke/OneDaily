package tmnt.example.onedaily.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import rx.schedulers.Schedulers;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.ui.common.Common;

/**
 * 图像相关工具类
 * Created by tmnt on 2016/6/6.
 */
public class ImageUtils {

    private static String path;
    private static String IMAGE_PATH = "oneDaily_image";

    /**
     * 进入相机
     */
    public static void toCamera(Activity activity, String path, int responesCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(new File(path));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, responesCode);
    }

    /**
     * 进入相册
     */
    public static void toGallery(int responesCode, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, responesCode);
    }

    /**
     * 从相册中获取图片
     *
     * @param context 当前上下文
     * @param data    intent
     * @return 将图片路径返回
     */
    public static String getImagePathFromGallery(Context context, Intent data) {
        Cursor cursor = context.getContentResolver().query(data.getData(), null, null, null, null, null);
        cursor.moveToNext();
        String image = cursor.getString(cursor.getColumnIndex("_data"));
        cursor.close();
        return image;
    }

    /**
     * 缩放图片
     *
     * @param context 当前上下文
     * @param resId   资源文件名
     * @return 返回Bitmap
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 获取内存卡中图片文件
     *
     * @param context
     * @param filename
     * @return
     */
    public static Bitmap readBitMap(Context context, String filename) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        path = filename;
        return BitmapFactory.decodeFile(filename, options);
    }

    /**
     * 保存bitmap
     *
     * @param bitmap
     * @param callBack
     */
    public static void saveBitmap(Bitmap bitmap, CallBack<File> callBack) {
        String fileName = "oneDaily_splash" + System.currentTimeMillis() + ".jpg";
        RxUilt.getInstance().createAndResult(Schedulers.io(), () -> {
            File file = IOUtil.createFile(Common.ONEDAILY_PATH
                    + File.separator + IMAGE_PATH
                    + File.separator + fileName);
            OutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            return file;
        }, callBack);

    }

    public static String getBitmapPath() {
        return path;
    }
}
