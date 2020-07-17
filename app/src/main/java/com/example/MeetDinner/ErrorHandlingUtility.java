package com.example.MeetDinner;

import com.example.MeetDinner.API.RetrofitClientInstance;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErrorHandlingUtility {
    public static ErrorModel parseError(Response<?> response) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( RetrofitClientInstance.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorModel errorModel = null;

        try {
            Converter<ResponseBody, ErrorModel> converter = retrofit.responseBodyConverter(ErrorModel.class, new Annotation[0]);
            if (response.errorBody() != null) {
                errorModel = converter.convert(response.errorBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return errorModel;
    }
}
