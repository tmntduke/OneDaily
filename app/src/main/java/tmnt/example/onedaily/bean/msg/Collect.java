package tmnt.example.onedaily.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tmnt on 2017/5/12.
 */

public class Collect implements Parcelable {
    private String id;
    private String author;
    private String image;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collect() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeString(this.image);
        dest.writeString(this.title);
    }

    protected Collect(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.image = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Collect> CREATOR = new Creator<Collect>() {
        @Override
        public Collect createFromParcel(Parcel source) {
            return new Collect(source);
        }

        @Override
        public Collect[] newArray(int size) {
            return new Collect[size];
        }
    };
}
