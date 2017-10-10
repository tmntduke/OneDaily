package tmnt.example.onedaily.ui.main.activity;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.ui.common.BaseActivity;

/**
 * Created by tmnt on 2017/10/10.
 */
@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity {
    @Bind(R.id.btnBack)
    ImageView mBtnBack;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {
        mBtnBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
