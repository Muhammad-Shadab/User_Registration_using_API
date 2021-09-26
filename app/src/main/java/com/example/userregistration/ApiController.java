package com.example.userregistration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    private static final String url = "http://192.168.1.7/UserForm/";
    private static ApiController apiController;
    private static Retrofit retrofit;

    ApiController(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiController getInstance()
    {
        if (apiController == null)
        {
            apiController = new ApiController();
        }
        return apiController;
    }

    ApiSet apiSet()
    {
        return retrofit.create(ApiSet.class);
    }

}
