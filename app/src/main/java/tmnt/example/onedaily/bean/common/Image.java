package tmnt.example.onedaily.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Image implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.large);
        dest.writeString(this.medium);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.small = in.readString();
        this.large = in.readString();
        this.medium = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}

