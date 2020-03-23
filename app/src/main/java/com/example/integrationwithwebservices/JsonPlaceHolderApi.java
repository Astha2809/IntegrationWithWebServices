package com.example.integrationwithwebservices;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET ("v2/posts.json")
    Call<model> getJsonObjectData();

}
