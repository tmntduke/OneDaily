package tmnt.example.onedaily.ui.gank.model;

import java.util.List;

import rx.Observable;
import tmnt.example.onedaily.api.Api;
import tmnt.example.onedaily.api.GankService;
import tmnt.example.onedaily.bean.gank.GankInfo;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.util.Common;

/**
 * Created by tmnt on 2017/4/12.
 */

public class GankNewsModel implements Model<Observable<List<GankInfo>>> {

    private Api mApi = Api.getInstance(Common.GANK_URL);
    private GankService service = mApi.getCall(GankService.class);

    private String category;

    public GankNewsModel(String category) {
        this.category = category;
    }

    @Override
    public Observable<List<GankInfo>> getNews() {

        Observable<List<GankInfo>> inListObservable = service.getGankNews(category, "10", "1");

        return inListObservable;
    }


    @Override
    public Observable<List<GankInfo>> refresh() {
        Observable<List<GankInfo>> inListObservable = service.getGankNews(category, "10", "1");
        return inListObservable;
    }

    @Override
    public Observable<List<GankInfo>> load(String page) {
        Observable<List<GankInfo>> inListObservable = service.getGankNews(category, "10", "1");
        return null;
    }
}
