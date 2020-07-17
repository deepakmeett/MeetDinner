package com.example.MeetDinner.API;
import com.example.MeetDinner.modals.ItemDetailsModel;
import com.example.MeetDinner.modals.VendorDetailsModel;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    
    public static final String BASE_URL = "http://ec2-3-7-248-87.ap-south-1.compute.amazonaws.com:8000/";
    
    @POST("getVendorInfo")
    Call<VendorDetailsModel> getVendorDetails(@Body JsonObject object);
    
    @GET("{id}/getVendorMenu")
    Call<List<ItemDetailsModel>> getItemDetails(@Path( "id" ) String vendorID);
    
    @POST("orderServices")
    Call<String> getDialogData(@Body JsonObject object);
}
