package tmnt.example.onedaily.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import tmnt.example.onedaily.bean.DoubanMovieInfo;
import tmnt.example.onedaily.bean.MovieDetailIInfo;

/**
 * Created by tmnt on 2017/4/13.
 */

public interface DoubanService {

    @GET("movie/{category}")
    Observable<List<DoubanMovieInfo>> getMovieList(@Path("category") String category);

    @GET("movie/{id}")
    Observable<MovieDetailIInfo> getMovieDetail(@Path("id") String id);

}
