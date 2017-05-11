package tmnt.example.onedaily.ui.main.presenter;

import java.util.List;

import tmnt.example.onedaily.bean.note.NoteInfo;
import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/5/11.
 */

public class NoteListPersenter extends BasePresenter<List<NoteInfo>> {

    public NoteListPersenter(Model<List<NoteInfo>> model, View<List<NoteInfo>> view) {
        super(model, view);
    }

    @Override
    public void handleData() {
        mModel.getNews(new CallBack<List<NoteInfo>>() {
            @Override
            public void onSuccess(List<NoteInfo> noteInfos) {
                mView.showData(noteInfos);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void handleLoad(String page) {

    }

    @Override
    public void handleRefresh(String page) {
        mModel.getNews(new CallBack<List<NoteInfo>>() {
            @Override
            public void onSuccess(List<NoteInfo> noteInfos) {
                mView.showData(noteInfos);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
