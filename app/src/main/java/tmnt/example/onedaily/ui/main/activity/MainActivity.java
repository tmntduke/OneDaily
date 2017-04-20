package tmnt.example.onedaily.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.douban.fragment.BookFragment;
import tmnt.example.onedaily.ui.douban.fragment.BookPageFragment;
import tmnt.example.onedaily.util.SharedPreferencesUtil;
import tmnt.example.onedaily.weight.BottomNavigation.BottomNavigationLayout;
import tmnt.example.onedaily.weight.BottomNavigation.Controller;
import tmnt.example.onedaily.weight.BottomNavigation.OnTabItemSelectListener;
import tmnt.example.onedaily.weight.BottomNavigation.TabItem;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.bottom_na)
    BottomNavigationLayout mBottom;

    private Controller controller;
    private SharedPreferencesUtil util;

    @Override
    public void initData(Bundle savedInstanceState) {
        util = SharedPreferencesUtil.getInstance(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TabItem tabItem = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorPrimary))
                .setTest("首页")
                .setRes(R.drawable.ic_tab_home)
                .setTag("home")
                .build();

        TabItem tabItem1 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorPrimary))
                .setTest("影音")
                .setRes(R.drawable.ic_tab_movie)
                .setTag("rr")
                .build();

        TabItem tabItem2 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorPrimary))
                .setRes(R.drawable.ic_tab_write)
                .setTag("write")
                .build();

        TabItem tabItem3 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorPrimary))
                .setTest("聊天")
                .setRes(R.drawable.ic_tab_chat)
                .setTag("notification")
                .build();

        TabItem tabItem4 = new TabItem.Builder(this)
                .setDefaultColor(0xFFACACAC)
                .setSelectColor(getResources().getColor(R.color.colorPrimary))
                .setTest("我的")
                .setRes(R.drawable.ic_tab_person)
                .setTag("person")
                .build();

        controller = mBottom.create()
                .addTabItem(tabItem)
                .addTabItem(tabItem1)
                .addTabItem(tabItem2)
                .addTabItem(tabItem3)
                .addTabItem(tabItem4)
                .build();

    }

    @Override
    public void initOperation() {

        //setFragment(R.id.main_contain,);

        controller.setSelect(0);
        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                Log.i(TAG, "onSelected: "+index);
                switch (index) {
                    case 0:

                        break;
                    case 1:
                        toFragment(R.id.main_contain, BookPageFragment.getInstance());
                        break;
                }
            }

            @Override
            public void onRepeatClick(int index, Object tag) {

            }
        });
    }

    @Override
    public void loadData() {

    }
}
