package io.flowerpower.rent.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price implements Parcelable {

    @SerializedName("usd")
    @Expose
    private Integer usd;
    @SerializedName("byr")
    @Expose
    private Integer byr;

    /**
     * @return The usd
     */
    public Integer getUsd() {
        return usd;
    }

    /**
     * @param usd The usd
     */
    public void setUsd(Integer usd) {
        this.usd = usd;
    }

    /**
     * @return The byr
     */
    public Integer getByr() {
        return byr;
    }

    /**
     * @param byr The byr
     */
    public void setByr(Integer byr) {
        this.byr = byr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.usd);
        dest.writeValue(this.byr);
    }

    public Price() {
    }

    protected Price(Parcel in) {
        this.usd = (Integer) in.readValue(Integer.class.getClassLoader());
        this.byr = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}