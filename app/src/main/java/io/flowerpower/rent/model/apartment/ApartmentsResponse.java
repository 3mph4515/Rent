package io.flowerpower.rent.model.apartment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public class ApartmentsResponse implements Parcelable {

    @SerializedName("apartments")
    @Expose
    private List<Apartment> apartments = new ArrayList<Apartment>();
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("page")
    @Expose
    private Page page;

    /**
     * @return The apartments
     */
    public List<Apartment> getApartments() {
        return apartments;
    }

    /**
     * @param apartments The apartments
     */
    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    /**
     * @return The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return The page
     */
    public Page getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.apartments);
        dest.writeValue(this.total);
        dest.writeParcelable(this.page, flags);
    }

    public ApartmentsResponse() {
    }

    protected ApartmentsResponse(Parcel in) {
        this.apartments = new ArrayList<Apartment>();
        in.readList(this.apartments, Apartment.class.getClassLoader());
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
        this.page = in.readParcelable(Page.class.getClassLoader());
    }

    public static final Parcelable.Creator<ApartmentsResponse> CREATOR = new Parcelable.Creator<ApartmentsResponse>() {
        @Override
        public ApartmentsResponse createFromParcel(Parcel source) {
            return new ApartmentsResponse(source);
        }

        @Override
        public ApartmentsResponse[] newArray(int size) {
            return new ApartmentsResponse[size];
        }
    };
}
