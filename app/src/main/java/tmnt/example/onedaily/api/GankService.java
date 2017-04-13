package tmnt.example.onedaily.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import tmnt.example.onedaily.bean.GankDaysInfo;
import tmnt.example.onedaily.bean.GankInfo;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface GankService {
    @GET("data/{category}/{size}/{page}")
    Observable<List<GankInfo>> getGankNews(@Path("category") String category, @Path("size") String size, @Path("page") String page);

    @GET("day/{year}/{month}/{day}")
    Observable<List<GankDaysInfo>> getGankDays(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
