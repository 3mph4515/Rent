package io.flowerpower.rent.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page implements Parcelable {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("items")
    @Expose
    private Integer items;
    @SerializedName("current")
    @Expose
    private Integer current;
    @SerializedName("last")
    @Expose
    private Integer last;

    /**
     * @return The limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit The limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return The items
     */
    public Integer getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(Integer items) {
        this.items = items;
    }

    /**
     * @return The current
     */
    public Integer getCurrent() {
        return current;
    }

    /**
     * @param current The current
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }

    /**
     * @return The last
     */
    public Integer getLast() {
        return last;
    }

    /**
     * @param last The last
     */
    public void setLast(Integer last) {
        this.last = last;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.limit);
        dest.writeValue(this.items);
        dest.writeValue(this.current);
        dest.writeValue(this.last);
    }

    public Page() {
    }

    protected Page(Parcel in) {
        this.limit = (Integer) in.readValue(Integer.class.getClassLoader());
        this.items = (Integer) in.readValue(Integer.class.getClassLoader());
        this.current = (Integer) in.readValue(Integer.class.getClassLoader());
        this.last = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Page> CREATOR = new Parcelable.Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel source) {
            return new Page(source);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };
}