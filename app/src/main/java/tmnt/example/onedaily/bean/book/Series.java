package tmnt.example.onedaily.bean.book;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Series implements Parcelable{
    /**
     * id : 660
     * title : 图灵程序设计丛书
     */

    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    public Series() {
    }

    protected Series(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel source) {
            return new Series(source);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };
}
