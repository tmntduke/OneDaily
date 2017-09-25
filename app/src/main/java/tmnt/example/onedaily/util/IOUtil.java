package tmnt.example.onedaily.util;

import android.telecom.Call;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import rx.schedulers.Schedulers;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.mvp.CallBack;

/**
 * Created by tmnt on 2017/5/8.
 */

public class IOUtil {

    private static RxUilt mRxUilt = RxUilt.getInstance();

    public static File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    public static void input(final File file, CallBack<String> callBack) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        mRxUilt.createAndResult(Schedulers.io(), () -> {
            try {
                byte[] bytes = new byte[1024];
                int foot = 0;
                int temp = 0;
                while ((temp = inputStream.read()) != -1) {
                    bytes[foot++] = (byte) temp;
                }
                return new String(bytes, 0, foot);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                inputStream.close();
            }
            return null;
        }, callBack);


    }

    public static void output(File file, byte[] bytes) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        mRxUilt.createAndResult(Schedulers.io(), () -> {
            try {
                outputStream.write(bytes);
            } finally {
                outputStream.close();
            }
            return true;
        }, new CallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

}
