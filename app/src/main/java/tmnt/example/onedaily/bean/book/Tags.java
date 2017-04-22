package tmnt.example.onedaily.bean.book;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Tags implements Parcelable{

    /**
     * count : 1
     * name : python入门
     * title : python入门
     */

    private int count;
    private String name;
    private String title;

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
        dest.writeInt(this.count);
        dest.writeString(this.name);
        dest.writeString(this.title);
    }

    public Tags() {
    }

    protected Tags(Parcel in) {
        this.count = in.readInt();
        this.name = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Tags> CREATOR = new Creator<Tags>() {
        @Override
        public Tags createFromParcel(Parcel source) {
            return new Tags(source);
        }

        @Override
        public Tags[] newArray(int size) {
            return new Tags[size];
        }
    };
}

