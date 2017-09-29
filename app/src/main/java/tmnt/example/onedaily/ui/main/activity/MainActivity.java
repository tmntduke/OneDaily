package tmnt.example.onedaily.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.douban.fragment.BookPageFragment;
import tmnt.example.onedaily.ui.douban.listener.OnBookRetrunListener;
import tmnt.example.onedaily.ui.main.fragment.MsgFragment;
import tmnt.example.onedaily.ui.turing.fragment.TuringChatFragment;
import tmnt.example.onedaily.ui.zhihu.fragment.ZhihuFregment;
import tmnt.example.onedaily.util.PremissionUtil;
import tmnt.example.onedaily.util.SharedPreferencesUtil;
import tmnt.example.onedaily.util.SystemUtils;
import tmnt.example.onedaily.weight.BottomNavigation.BottomNavigationLayout;
import tmnt.example.onedaily.weight.BottomNavigation.Controller;
import tmnt.example.onedaily.weight.BottomNavigation.OnTabItemSelectListener;
import tmnt.example.onedaily.weight.BottomNavigation.TabItem;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.bottom_na)
    BottomNavigationLayout mBottom;

    private Controller controller;
    private SharedPreferencesUtil util;
    private int mIndex;

    private static OnBookRetrunListener mOnBookRetrunListener;
    private boolean isExit;

    @Override
    public void initData(Bundle savedInstanceState) {
        util = SharedPreferencesUtil.getInstance(this);
    }

    @Override
    public void initView() {

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

        Fragment zhihu = ZhihuFregment.getInstance();
        setFragment(R.id.main_contain, zhihu);
    }

    @Override
    public void initOperation() {

        controller.setSelect(0);
        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                Log.i(TAG, "onSelected: " + index);
                switch (index) {
                    case 0:
                        Fragment zhihu = ZhihuFregment.getInstance();
                        mIndex = index;
                        toFragment(R.id.main_contain, zhihu);
                        break;
                    case 1:
                        Fragment douban = BookPageFragment.getInstance();
                        mIndex = index;
                        if (douban.isVisible() && douban.isAdded()) {
                            if (mOnBookRetrunListener != null) {
                                mOnBookRetrunListener.onReturn();
                            }
                        } else {
                            toFragment(R.id.main_contain, douban);
                        }

                        break;
                    case 2:
                        toActivity(WriteArticleActivity.class);
                        break;
                    case 3:
                        if (!PremissionUtil.chaeckPermission(MainActivity.this
                                , "android.permission.READ_PHONE_STATE")) {
                            requestPermission();
                        }
                        Fragment chat = TuringChatFragment.getInstance();
                        mIndex = index;
                        toFragment(R.id.main_contain, chat);
                        break;
                    case 4:
                        Fragment msg = MsgFragment.getInstance();
                        mIndex = index;
                        toFragment(R.id.main_contain, msg);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        mBottom.setSelect(mIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = mFragmentManager.findFragmentById(R.id.main_contain);
        if (fragment instanceof MsgFragment) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                SystemUtils.showToast(MainActivity.this, "click is again");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                return false;
            } else {
                //Log.i("onKeyDown", "start");
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOnBookRetrunListener != null) {
            mOnBookRetrunListener = null;
        }
    }

    public static void setOnBookRetrunListener(OnBookRetrunListener onBookRetrunListener) {
        mOnBookRetrunListener = onBookRetrunListener;
    }

    private void requestPermission() {
        PremissionUtil.requestPermission(this, new String[]{"android.permission.RECORD_AUDIO"
                , "android.permission.ACCESS_NETWORK_STATE"
                , "android.permission.READ_PHONE_STATE"
                , "android.permission.WRITE_EXTERNAL_STORAGE"
                , "android.permission.READ_CONTACTS"
                , "android.permission.ACCESS_WIFI_STATE"
                , "android.permission.WRITE_SETTINGS"});
    }
}
