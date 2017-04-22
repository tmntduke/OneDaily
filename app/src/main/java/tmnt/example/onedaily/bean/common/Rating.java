package tmnt.example.onedaily.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tmnt on 2017/4/17.
 */

public class Rating implements Parcelable{

        /**
         * max : 10
         * numRaters : 1
         * average : 0.0
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;

        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.max);
        dest.writeInt(this.numRaters);
        dest.writeString(this.average);
        dest.writeInt(this.min);
    }

    public Rating() {
    }

    protected Rating(Parcel in) {
        this.max = in.readInt();
        this.numRaters = in.readInt();
        this.average = in.readString();
        this.min = in.readInt();
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}


