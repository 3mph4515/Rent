package io.flowerpower.rent.model.clustering;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feature implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("properties")
    @Expose
    private Properties properties;

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

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * @param geometry The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * @return The properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties The properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeValue(this.id);
        dest.writeParcelable(this.geometry, flags);
        dest.writeParcelable(this.properties, flags);
    }

    public Feature() {
    }

    protected Feature(Parcel in) {
        this.type = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.geometry = in.readParcelable(Geometry.class.getClassLoader());
        this.properties = in.readParcelable(Properties.class.getClassLoader());
    }

    public static final Parcelable.Creator<Feature> CREATOR = new Parcelable.Creator<Feature>() {
        @Override
        public Feature createFromParcel(Parcel source) {
            return new Feature(source);
        }

        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };
}