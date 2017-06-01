package tmnt.example.onedaily.bean.chat;

/**
 * Created by tmnt on 2017/6/1.
 */

public class ChatInfo {
    private String msg;
    private int type;

    public ChatInfo(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
