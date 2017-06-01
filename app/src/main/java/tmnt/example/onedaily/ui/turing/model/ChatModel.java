package tmnt.example.onedaily.ui.turing.model;

import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;

/**
 * Created by tmnt on 2017/6/1.
 */

public class ChatModel implements Model<String> {

    private String ask;

    public ChatModel(String ask) {
        this.ask = ask;
    }

    @Override
    public void getNews(CallBack<String> callBack) {

    }

    @Override
    public void refresh(CallBack<String> callBack) {

    }

    @Override
    public void load(String page, CallBack<String> callBack) {

    }
}
