package tmnt.example.onedaily.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import tmnt.example.onedaily.bean.gank.PhotoInfo;

/**
 * Created by tmnt on 2017/10/11.
 */

public interface GankService {
    @GET("data/福利/16/{page}")
    Observable<PhotoInfo> getPhoto(@Path("page") String page);
}
