package io.flowerpower.rent.model.apartment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Converted implements Parcelable {

    @SerializedName("BYR")
    @Expose
    private BYR bYR;
    @SerializedName("BYN")
    @Expose
    private BYN bYN;
    @SerializedName("USD")
    @Expose
    private USD uSD;

    /**
     * @return The bYR
     */
    public BYR getBYR() {
        return bYR;
    }

    /**
     * @param bYR The BYR
     */
    public void setBYR(BYR bYR) {
        this.bYR = bYR;
    }

    /**
     * @return The bYN
     */
    public BYN getBYN() {
        return bYN;
    }

    /**
     * @param bYN The BYN
     */
    public void setBYN(BYN bYN) {
        this.bYN = bYN;
    }

    /**
     * @return The uSD
     */
    public USD getUSD() {
        return uSD;
    }

    /**
     * @param uSD The USD
     */
    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.bYR, flags);
        dest.writeParcelable(this.bYN, flags);
        dest.writeParcelable(this.uSD, flags);
    }

    public Converted() {
    }

    protected Converted(Parcel in) {
        this.bYR = in.readParcelable(BYR.class.getClassLoader());
        this.bYN = in.readParcelable(BYN.class.getClassLoader());
        this.uSD = in.readParcelable(USD.class.getClassLoader());
    }

    public static final Parcelable.Creator<Converted> CREATOR = new Parcelable.Creator<Converted>() {
        @Override
        public Converted createFromParcel(Parcel source) {
            return new Converted(source);
        }

        @Override
        public Converted[] newArray(int size) {
            return new Converted[size];
        }
    };
}