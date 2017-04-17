package tmnt.example.onedaily.bean.movie;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Director {

    /**
     * alt : https://movie.douban.com/celebrity/1009396/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/4451.jpg","large":"https://img3.doubanio.com/img/celebrity/large/4451.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/4451.jpg"}
     * name : F·加里·格雷
     * id : 1009396
     */

    private String alt;
    private Avatar avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatar getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatar avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

