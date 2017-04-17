package tmnt.example.onedaily.bean.common;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Image {
    /**
     * small : https://img3.doubanio.com/spic/s27175520.jpg
     * large : https://img3.doubanio.com/lpic/s27175520.jpg
     * medium : https://img3.doubanio.com/mpic/s27175520.jpg
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}

