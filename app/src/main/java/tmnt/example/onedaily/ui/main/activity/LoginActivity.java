package tmnt.example.onedaily.ui.main.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.util.ShareUtil;

/**
 * Created by tmnt on 2017/10/12.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @Bind(R.id.app_name)
    TextView mAppName;
    @Bind(R.id.img_weibo)
    ImageView mImgWeibo;
    @Bind(R.id.img_qq)
    ImageView mImgQq;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mImgQq.setOnClickListener(v -> {
            ShareUtil.otherLogin(QQ.NAME, this);
        });

        mImgWeibo.setOnClickListener(v -> {
            ShareUtil.otherLogin(SinaWeibo.NAME, this);
        });
    }

    @Override
    public void initOperation() {

    }

    @Override
    public void loadData() {

    }
}
