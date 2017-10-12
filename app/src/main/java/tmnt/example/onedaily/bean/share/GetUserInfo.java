package tmnt.example.onedaily.bean.share;

/**
 * 第三方登录获取的用户信息
 * Created by tmnt on 2016/11/28.
 */
public class GetUserInfo {
    private String type;
    private String icon;
    private String nickName;
    private String account;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
