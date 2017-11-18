package cn.sharesdk.onekeyshare;

import cn.sharesdk.framework.authorize.AuthorizeAdapter;

/**
 * Created by tmnt on 2016/11/25.
 */
public class MyAdapter extends AuthorizeAdapter {

    @Override
    public void onCreate() {
        super.onCreate();
        hideShareSDKLogo();
    }
}
