package com.example.MeetDinner.API;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClientInstance {

    private static Retrofit retrofitDetails;
    
    public static final String BASE_URL = "http://ec2-3-7-248-87.ap-south-1.compute.amazonaws.com:8000/";
    
    public static Retrofit getRetrofitInstanceDetails(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient innerClientNotification = new OkHttpClient.Builder()
                .connectTimeout( 2, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES) // read timeout
                .addInterceptor(logging) //logging
                .build();
        if (retrofitDetails == null) {
            retrofitDetails = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(innerClientNotification)
                    .addConverterFactory( GsonConverterFactory.create())
                    .build();
        }
        return retrofitDetails;
    }

}
