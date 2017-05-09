package tmnt.example.onedaily.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import tmnt.example.onedaily.Rx.RxUilt;

/**
 * Created by tmnt on 2017/5/8.
 */

public class IOUtil {

    public static String input(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int foot = 0;
        int temp = 0;
        while ((temp = inputStream.read()) != -1) {
            bytes[foot++] = (byte) temp;
        }

        return new String(bytes, 0, foot);
    }

    public static void output(File file, byte[] bytes) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
    }

}
