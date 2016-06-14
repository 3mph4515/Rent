package io.flowerpower.rent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raman Branavitski on 6/14/16.
 */
public class AutoCompleteAddressResponse {

    @Expose
    @SerializedName("error_message")
    private String errorMessage;

    private List<Address> predictions;

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Address> getPredictions() {
        return predictions;
    }
}
