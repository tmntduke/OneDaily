package tmnt.example.onedaily.bean;

import java.util.List;

/**
 * Created by tmnt on 2017/4/13.
 */

public class MovieDetailIInfo {

    /**
     * rating : {"max":10,"average":"7.0","numRaters":1069,"min":0}
     * author : [{"name":"F·加里·格雷 F. Gary Gray"}]
     * alt_title : 速度与激情8 / 狂野时速8(港)
     * image : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2444256500.webp
     * title : The Fate of the Furious
     * summary : 多米尼克与莱蒂共度蜜月，布莱恩与米娅退出了赛车界，这支曾环游世界的顶级飞车家族队伍的生活正渐趋平淡。然而，一位神秘女子Cipher的出现，令整个队伍卷入信任与背叛的危机，面临前所未有的考验。
     * attrs : {"language":["英语"],"pubdate":["2017-04-12(法国)","2017-04-14(美国/中国大陆)"],"title":["The Fate of the Furious"],"country":["美国","日本","法国","加拿大","美属萨摩亚"],"writer":["盖瑞·斯科特·汤普森 Gary Scott Thompson"],"director":["F·加里·格雷 F. Gary Gray"],"cast":["范·迪塞尔 Vin Diesel","道恩·强森 Dwayne Johnson","查理兹·塞隆 Charlize Theron","杰森·斯坦森 Jason Statham","米歇尔·罗德里格兹 Michelle Rodriguez","娜塔莉·伊曼纽尔 Nathalie Emmanuel","埃尔莎·帕塔奇 Elsa Pataky","海伦·米伦 Helen Mirren","泰瑞斯·吉布森 Tyrese Gibson","库尔特·拉塞尔 Kurt Russell","乔丹娜·布鲁斯特 Jordana Brewster","卢卡斯·布莱克 Lucas Black","卢达克里斯 Ludacris","克里斯托弗·海维尤 Kristofer Hivju","斯科特·伊斯特伍德 Scott Eastwood"],"movie_duration":["136分钟"],"year":["2017"],"movie_type":["动作","犯罪"]}
     * id : https://api.douban.com/movie/26260853
     * mobile_link : https://m.douban.com/movie/subject/26260853/
     * alt : https://movie.douban.com/movie/26260853
     * tags : [{"count":1476,"name":"动作"},{"count":1294,"name":"赛车"},{"count":1073,"name":"美国"},{"count":1020,"name":"飙车"},{"count":978,"name":"2017"},{"count":933,"name":"犯罪"},{"count":881,"name":"飙车的视觉艺术"},{"count":854,"name":"跑车"}]
     */

    private RatingBean rating;
    private String alt_title;
    private String image;
    private String title;
    private String summary;
    private AttrsBean attrs;
    private String id;
    private String mobile_link;
    private String alt;
    private List<AuthorBean> author;
    private List<TagsBean> tags;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public AttrsBean getAttrs() {
        return attrs;
    }

    public void setAttrs(AttrsBean attrs) {
        this.attrs = attrs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public List<AuthorBean> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorBean> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 7.0
         * numRaters : 1069
         * min : 0
         */

        private int max;
        private String average;
        private int numRaters;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class AttrsBean {
        private List<String> language;
        private List<String> pubdate;
        private List<String> title;
        private List<String> country;
        private List<String> writer;
        private List<String> director;
        private List<String> cast;
        private List<String> movie_duration;
        private List<String> year;
        private List<String> movie_type;

        public List<String> getLanguage() {
            return language;
        }

        public void setLanguage(List<String> language) {
            this.language = language;
        }

        public List<String> getPubdate() {
            return pubdate;
        }

        public void setPubdate(List<String> pubdate) {
            this.pubdate = pubdate;
        }

        public List<String> getTitle() {
            return title;
        }

        public void setTitle(List<String> title) {
            this.title = title;
        }

        public List<String> getCountry() {
            return country;
        }

        public void setCountry(List<String> country) {
            this.country = country;
        }

        public List<String> getWriter() {
            return writer;
        }

        public void setWriter(List<String> writer) {
            this.writer = writer;
        }

        public List<String> getDirector() {
            return director;
        }

        public void setDirector(List<String> director) {
            this.director = director;
        }

        public List<String> getCast() {
            return cast;
        }

        public void setCast(List<String> cast) {
            this.cast = cast;
        }

        public List<String> getMovie_duration() {
            return movie_duration;
        }

        public void setMovie_duration(List<String> movie_duration) {
            this.movie_duration = movie_duration;
        }

        public List<String> getYear() {
            return year;
        }

        public void setYear(List<String> year) {
            this.year = year;
        }

        public List<String> getMovie_type() {
            return movie_type;
        }

        public void setMovie_type(List<String> movie_type) {
            this.movie_type = movie_type;
        }
    }

    public static class AuthorBean {
        /**
         * name : F·加里·格雷 F. Gary Gray
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TagsBean {
        /**
         * count : 1476
         * name : 动作
         */

        private int count;
        private String name;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
