package io.flowerpower.rent.model.clustering;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties implements Parcelable {

    @SerializedName("balloonContent")
    @Expose
    private String balloonContent;
    @SerializedName("clusterCaption")
    @Expose
    private String clusterCaption;
    @SerializedName("hintContent")
    @Expose
    private String hintContent;

    /**
     * @return The balloonContent
     */
    public String getBalloonContent() {
        return balloonContent;
    }

    /**
     * @param balloonContent The balloonContent
     */
    public void setBalloonContent(String balloonContent) {
        this.balloonContent = balloonContent;
    }

    /**
     * @return The clusterCaption
     */
    public String getClusterCaption() {
        return clusterCaption;
    }

    /**
     * @param clusterCaption The clusterCaption
     */
    public void setClusterCaption(String clusterCaption) {
        this.clusterCaption = clusterCaption;
    }

    /**
     * @return The hintContent
     */
    public String getHintContent() {
        return hintContent;
    }

    /**
     * @param hintContent The hintContent
     */
    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.balloonContent);
        dest.writeString(this.clusterCaption);
        dest.writeString(this.hintContent);
    }

    public Properties() {
    }

    protected Properties(Parcel in) {
        this.balloonContent = in.readString();
        this.clusterCaption = in.readString();
        this.hintContent = in.readString();
    }

    public static final Parcelable.Creator<Properties> CREATOR = new Parcelable.Creator<Properties>() {
        @Override
        public Properties createFromParcel(Parcel source) {
            return new Properties(source);
        }

        @Override
        public Properties[] newArray(int size) {
            return new Properties[size];
        }
    };
}