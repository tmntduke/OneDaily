package tmnt.example.onedaily.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment 基类 提供模板
 * Created by tmnt on 2017/4/11.
 */

public abstract class BaseFragment extends Fragment implements BaseFunc {

    private boolean isCreate;
    private boolean isVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isCreate = true;
        View view = setContentView(inflater, container, savedInstanceState);
        initView();
        initOperation();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    private void lazyLoad() {
        if (!isVisible || !isCreate) {
            return;
        }
        loadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            loadData();
        }
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 跳转activity
     *
     * @param clazz
     */
    protected void toActivity(Class<? extends BaseActivity> clazz) {
        toActivity(clazz, null);
    }

    protected void toActivity(Class<? extends BaseActivity> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
