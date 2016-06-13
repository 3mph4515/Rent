package io.flowerpower.rent.model.apartment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecificAdvertPrice implements Parcelable {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("converted")
    @Expose
    private Converted converted;

    /**
     * @return The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return The converted
     */
    public Converted getConverted() {
        return converted;
    }

    /**
     * @param converted The converted
     */
    public void setConverted(Converted converted) {
        this.converted = converted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.amount);
        dest.writeString(this.currency);
        dest.writeParcelable(this.converted, flags);
    }

    public SpecificAdvertPrice() {
    }

    protected SpecificAdvertPrice(Parcel in) {
        this.amount = in.readString();
        this.currency = in.readString();
        this.converted = in.readParcelable(Converted.class.getClassLoader());
    }

    public static final Parcelable.Creator<SpecificAdvertPrice> CREATOR = new Parcelable.Creator<SpecificAdvertPrice>() {
        @Override
        public SpecificAdvertPrice createFromParcel(Parcel source) {
            return new SpecificAdvertPrice(source);
        }

        @Override
        public SpecificAdvertPrice[] newArray(int size) {
            return new SpecificAdvertPrice[size];
        }
    };
}