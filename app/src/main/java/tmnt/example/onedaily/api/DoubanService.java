package tmnt.example.onedaily.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.bean.movie.DoubanMovieInfo;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;

/**
 * 豆瓣api申请
 * Created by tmnt on 2017/4/13.
 */

public interface DoubanService {

    @GET("movie/{category}")
    Observable<List<DoubanMovieInfo>> getMovie(@Path("category") String category);

    @GET("book/search")
    Observable<DoubanBookInfo> getBook(@Query("q") String q, @Query("start") String start, @Query("count") String count);

    @GET("book/isbn/{name}")
    Observable<Book> getBookForIsbn(@Path("name") String name);

    @GET("book/{id}")
    Observable<Book> getBookDetail(@Path("id")String id);

}
