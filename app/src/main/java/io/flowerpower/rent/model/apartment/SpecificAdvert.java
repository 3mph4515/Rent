package io.flowerpower.rent.model.apartment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecificAdvert implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price")
    @Expose
    private SpecificAdvertPrice price;
    @SerializedName("rent_type")
    @Expose
    private String rentType;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("last_time_up")
    @Expose
    private String lastTimeUp;
    @SerializedName("up_available_in")
    @Expose
    private Integer upAvailableIn;
    @SerializedName("url")
    @Expose
    private String url;

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
     * @return The price
     */
    public SpecificAdvertPrice getSpecificAdvertPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setSpecificAdvertPrice(SpecificAdvertPrice price) {
        this.price = price;
    }

    /**
     * @return The rentType
     */
    public String getRentType() {
        return rentType;
    }

    /**
     * @param rentType The rent_type
     */
    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    /**
     * @return The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return The contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact The contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The lastTimeUp
     */
    public String getLastTimeUp() {
        return lastTimeUp;
    }

    /**
     * @param lastTimeUp The last_time_up
     */
    public void setLastTimeUp(String lastTimeUp) {
        this.lastTimeUp = lastTimeUp;
    }

    /**
     * @return The upAvailableIn
     */
    public Integer getUpAvailableIn() {
        return upAvailableIn;
    }

    /**
     * @param upAvailableIn The up_available_in
     */
    public void setUpAvailableIn(Integer upAvailableIn) {
        this.upAvailableIn = upAvailableIn;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.price, flags);
        dest.writeString(this.rentType);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.photo);
        dest.writeParcelable(this.contact, flags);
        dest.writeString(this.createdAt);
        dest.writeString(this.lastTimeUp);
        dest.writeValue(this.upAvailableIn);
        dest.writeString(this.url);
    }

    public SpecificAdvert() {
    }

    protected SpecificAdvert(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.price = in.readParcelable(SpecificAdvertPrice.class.getClassLoader());
        this.rentType = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.photo = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.createdAt = in.readString();
        this.lastTimeUp = in.readString();
        this.upAvailableIn = (Integer) in.readValue(Integer.class.getClassLoader());
        this.url = in.readString();
    }

    public static final Parcelable.Creator<SpecificAdvert> CREATOR = new Parcelable.Creator<SpecificAdvert>() {
        @Override
        public SpecificAdvert createFromParcel(Parcel source) {
            return new SpecificAdvert(source);
        }

        @Override
        public SpecificAdvert[] newArray(int size) {
            return new SpecificAdvert[size];
        }
    };

    @Override
    public String toString() {
        return "SpecificAdvert{" +
                "id=" + id +
                ", price=" + price +
                ", rentType='" + rentType + '\'' +
                ", location=" + location +
                ", photo='" + photo + '\'' +
                ", contact=" + contact +
                ", createdAt='" + createdAt + '\'' +
                ", lastTimeUp='" + lastTimeUp + '\'' +
                ", upAvailableIn=" + upAvailableIn +
                ", url='" + url + '\'' +
                '}';
    }
}