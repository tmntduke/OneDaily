package tmnt.example.onedaily.event;

import tmnt.example.onedaily.bean.share.GetUserInfo;

/**
 * Created by tmnt on 2017/10/12.
 */

public class UserLoginEvent {

    public String mGetUserInfo;

    public UserLoginEvent(String getUserInfo) {
        mGetUserInfo = getUserInfo;
    }
}
