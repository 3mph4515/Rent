package io.flowerpower.rent.model.clustering;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PointsResponse implements Parcelable {

    @SerializedName("features")
    @Expose
    private List<Feature> features = new ArrayList<Feature>();
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * @return The features
     */
    public List<Feature> getFeatures() {
        return features;
    }

    /**
     * @param features The features
     */
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.features);
        dest.writeString(this.type);
    }

    public PointsResponse() {
    }

    protected PointsResponse(Parcel in) {
        this.features = in.createTypedArrayList(Feature.CREATOR);
        this.type = in.readString();
    }

    public static final Parcelable.Creator<PointsResponse> CREATOR = new Parcelable.Creator<PointsResponse>() {
        @Override
        public PointsResponse createFromParcel(Parcel source) {
            return new PointsResponse(source);
        }

        @Override
        public PointsResponse[] newArray(int size) {
            return new PointsResponse[size];
        }
    };
}