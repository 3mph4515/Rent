package io.flowerpower.rent.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Raman Branavitski on 6/14/16.
 */
public class Address {

    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
