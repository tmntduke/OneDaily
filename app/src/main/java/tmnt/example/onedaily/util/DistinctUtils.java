package tmnt.example.onedaily.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tmnt on 2017/4/23.
 */

public class DistinctUtils {
    private static final String TAG = "DistinctUtils";

    public static List<String> distinct(List<String> list) {
        Set<String> set = new HashSet<>();
        set.addAll(list);
        List<String> list1 = new ArrayList<>();
        list1.addAll(set);
        return list1;
    }
}
