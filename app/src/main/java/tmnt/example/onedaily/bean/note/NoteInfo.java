package tmnt.example.onedaily.bean.note;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tmnt on 2017/5/11.
 */

public class NoteInfo implements Parcelable {
    private String id;
    private String date;
    private String title;
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.date);
        dest.writeString(this.title);
        dest.writeString(this.path);
    }

    public NoteInfo() {
    }

    protected NoteInfo(Parcel in) {
        this.id = in.readString();
        this.date = in.readString();
        this.title = in.readString();
        this.path = in.readString();
    }

    public static final Creator<NoteInfo> CREATOR = new Creator<NoteInfo>() {
        @Override
        public NoteInfo createFromParcel(Parcel source) {
            return new NoteInfo(source);
        }

        @Override
        public NoteInfo[] newArray(int size) {
            return new NoteInfo[size];
        }
    };
}
