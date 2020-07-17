
package com.example.MeetDinner.modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorDetailsModel {

    @SerializedName("vendorName")
    @Expose
    private String vendorName;
    @SerializedName("address")
    @Expose
    private String address;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
