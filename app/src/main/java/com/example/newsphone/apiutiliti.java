package com.example.newsphone;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiutiliti {

    private static Retrofit retrofit=null;

    public static apiinterface apiinterface(){

        if (retrofit==null)
        {
            retrofit= new Retrofit.Builder().baseUrl(apiinterface.baseurl).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(apiinterface.class);

    }


}
