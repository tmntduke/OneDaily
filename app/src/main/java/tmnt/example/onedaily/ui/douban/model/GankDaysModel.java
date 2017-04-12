package tmnt.example.onedaily.ui.douban.model;

import java.util.Calendar;
import java.util.List;

import rx.Observable;
import tmnt.example.onedaily.api.Api;
import tmnt.example.onedaily.api.gank.GankService;
import tmnt.example.onedaily.bean.GankDaysInfo;
import tmnt.example.onedaily.util.Common;

/**
 * Created by tmnt on 2017/4/12.
 */

public class GankDaysModel implements Model<Observable<List<GankDaysInfo>>> {

    private Api mApi = Api.getInstance(Common.GANK_URL);
    private GankService mService = mApi.getCall(GankService.class);

    private String year;
    private String month;
    private String day;

    public GankDaysModel(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    @Override
    public Observable<List<GankDaysInfo>> getGankNews() {
        Observable<List<GankDaysInfo>> observable = mService.getGankDays(year, month, day);
        return observable;
    }

    @Override
    public Observable<List<GankDaysInfo>> refresh() {
        return null;
    }

    @Override
    public Observable<List<GankDaysInfo>> load(String page) {
        return null;
    }
}
