package tmnt.example.onedaily.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.util.SharedPreferencesUtil;
import tmnt.example.onedaily.weight.BottomNavigation.BottomNavigationLayout;
import tmnt.example.onedaily.weight.BottomNavigation.Controller;
import tmnt.example.onedaily.weight.BottomNavigation.OnTabItemSelectListener;
import tmnt.example.onedaily.weight.BottomNavigation.TabItem;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.activity_main)
    RelativeLayout mActivityMain;

    private static final String TAG = "MainActivity";
    @Bind(R.id.bottom_na)
    BottomNavigationLayout mBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        TabItem tabItem = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorAccent))
                .setTest("首页")
                .setRes(R.drawable.ic_home)
                .setTag("home")
                .build();

        TabItem tabItem1 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorAccent))
                .setTest("haha")
                .setRes(R.drawable.lsv)
                .setTag("rr")
                .build();

        TabItem tabItem2 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorAccent))
                .setTest("ni")
                .setRes(R.drawable.ic_home)
                .setTag("ll")
                .build();

        TabItem tabItem3 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorAccent))
                .setTest("wo")
                .setRes(R.drawable.lsv)
                .setTag("ja")
                .build();

        TabItem tabItem4 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorAccent))
                .setTest("wo")
                .setRes(R.drawable.lsv)
                .setTag("la")
                .build();

        //tabItem.setIcon(false);


        Log.i(TAG, "onCreate: " + tabItem);
        // mActivityMain.addView(tabItem);

        SharedPreferencesUtil util = SharedPreferencesUtil.getInstance(this);

        util.putData("hello", "hello");

        String s = util.getData("hello");
        Log.i(TAG, "onCreate: " + s);

        util.putData("first", 3);
        util.getData("h");

        Log.i(TAG, "onCreate: " + util.getData("h"));


        Controller controller = mBottom.create()
                .addTabItem(tabItem)
                .addTabItem(tabItem1)
                .addTabItem(tabItem2)
                .addTabItem(tabItem3)
                .addTabItem(tabItem4)
                .build();

        controller.setSelect(0);
        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                Log.i(TAG, "onSelected: " + index + "   " + tag);
            }

            @Override
            public void onRepeatClick(int index, Object tag) {

            }
        });
    }

}
