package tmnt.example.onedaily.util;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.share.GetUserInfo;
import tmnt.example.onedaily.bean.share.ShareInfo;
import tmnt.example.onedaily.event.UserLoginEvent;
import tmnt.example.onedaily.event.UserLogoutEvent;
import tmnt.example.onedaily.ui.common.Common;


/**
 * shareSDK封装类
 * Created by tmnt on 2016/11/28.
 */
public class ShareUtil {

    private static final String TAG = "ShareUtil";

    /**
     * 第三方登录
     */
    public static void otherLogin(String loginType, Context context) {

        Platform platform = ShareSDK.getPlatform(loginType);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行

        if (platform.isAuthValid()) {

            String userId = platform.getDb().getUserId();

        }
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ((Activity) context).finish();
                createUser(platform, loginType, context);
            }

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }


            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
//authorize与showUser单独调用一个即可
        //weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        platform.showUser(null);//授权并获取用户信息
    }

    private static void createUser(Platform platform, String type, Context context) {
        GetUserInfo info = new GetUserInfo();
        info.setNickName(platform.getDb().getUserName());
        info.setIcon(platform.getDb().getUserIcon());
        info.setAccount(platform.getDb().getUserId());
        info.setType(type);
        String userJson = new Gson().toJson(info);
        SharedPreferencesUtil.getInstance(context).putData(Common.USER_INFO, userJson);
        RxBus.getInstance().post(new UserLoginEvent(userJson));
    }

    public static void logout(String loginType, Context context) {
        Platform paPlatform = ShareSDK.getPlatform(loginType);
        if (paPlatform.isAuthValid()) {
            paPlatform.removeAccount(true);
            SharedPreferencesUtil.getInstance(context).removeData(Common.USER_INFO);
            RxBus.getInstance().post(new UserLogoutEvent());
        }
    }

    /**
     * 分享
     *
     * @param info
     */
    public static void share(ShareInfo info, Context context) {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(info.getTitle());
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(info.getTitleUrl());
// text是分享文本，所有平台都需要这个字段
        oks.setText(info.getText());
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImageUrl("http://tupian.enterdesk.com/2015/xll/02/27/1/zhuobielin8.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        //oks.setUrl("http://www.baidu.com");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(info.getSite());
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(info.getSiteUrl());

// 启动分享GUI
        oks.show(context);
    }

}
