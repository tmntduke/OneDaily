package tmnt.example.onedaily.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import tmnt.example.onedaily.bean.zhihu.ZhihuDetailInfo;
import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;

/**
 * Created by tmnt on 2017/4/24.
 */

public interface ZhihuService {
    @GET("latest")
    Observable<ZhihuInfo> getZhihuLeast();

    @GET("before/{date}")
    Observable<ZhihuInfo> getZhihuBefor(@Path("date") String date);

    @GET("{id}")
    Observable<ZhihuDetailInfo> getZhihuDetail(@Path("id") String id);
}
