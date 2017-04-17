package tmnt.example.onedaily.bean.movie;

import java.util.List;

import tmnt.example.onedaily.bean.common.Image;
import tmnt.example.onedaily.bean.common.Rating;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Subject {

    /**
     * rating : {"max":10,"average":7,"stars":"35","min":0}
     * genres : ["动作","犯罪"]
     * title : 速度与激情8
     * casts : [{"alt":"https://movie.douban.com/celebrity/1041020/","avatars":{"small":"https://img5.doubanio.com/img/celebrity/small/53186.jpg","large":"https://img5.doubanio.com/img/celebrity/large/53186.jpg","medium":"https://img5.doubanio.com/img/celebrity/medium/53186.jpg"},"name":"范·迪塞尔","id":"1041020"},{"alt":"https://movie.douban.com/celebrity/1044707/","avatars":{"small":"https://img5.doubanio.com/img/celebrity/small/196.jpg","large":"https://img5.doubanio.com/img/celebrity/large/196.jpg","medium":"https://img5.doubanio.com/img/celebrity/medium/196.jpg"},"name":"道恩·强森","id":"1044707"},{"alt":"https://movie.douban.com/celebrity/1018991/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/44470.jpg","large":"https://img3.doubanio.com/img/celebrity/large/44470.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/44470.jpg"},"name":"查理兹·塞隆","id":"1018991"}]
     * collect_count : 1150
     * original_title : The Fate of the Furious
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1009396/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/4451.jpg","large":"https://img3.doubanio.com/img/celebrity/large/4451.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/4451.jpg"},"name":"F·加里·格雷","id":"1009396"}]
     * year : 2017
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2444256500.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2444256500.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2444256500.webp"}
     * alt : https://movie.douban.com/subject/26260853/
     * id : 26260853
     */

    private Rating rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private Image images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<Cast> casts;
    private List<Director> directors;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }


}

