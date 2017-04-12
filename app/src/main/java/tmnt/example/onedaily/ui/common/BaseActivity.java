package tmnt.example.onedaily.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by tmnt on 2017/4/11.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseFunc {

    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        initData();
        initView();
        initOperation();
        loadData();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    /**
     * 跳转activity
     *
     * @param clazz
     */
    protected void toActivity(Class<? extends BaseActivity> clazz) {
        toActivity(clazz, null);
    }

    protected void toActivity(Class<? extends BaseActivity> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 设置fragment
     *
     * @param contain
     * @param fragment
     */
    protected void setFragment(int contain, Fragment fragment) {

        mFragmentManager.beginTransaction().add(contain, fragment).commit();
    }

    /**
     * fragment跳转
     *
     * @param contain
     * @param fragment
     */
    protected void toFragment(int contain, Fragment fragment) {
        mFragmentManager.beginTransaction().replace(contain, fragment).commit();
    }
}
