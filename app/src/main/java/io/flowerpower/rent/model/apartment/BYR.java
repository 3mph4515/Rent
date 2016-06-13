package io.flowerpower.rent.model.apartment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BYR implements Parcelable {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.amount);
        dest.writeString(this.currency);
    }

    public BYR() {
    }

    protected BYR(Parcel in) {
        this.amount = in.readString();
        this.currency = in.readString();
    }

    public static final Parcelable.Creator<BYR> CREATOR = new Parcelable.Creator<BYR>() {
        @Override
        public BYR createFromParcel(Parcel source) {
            return new BYR(source);
        }

        @Override
        public BYR[] newArray(int size) {
            return new BYR[size];
        }
    };
}