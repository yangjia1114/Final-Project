package com.example.yangjia.finalproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyApi {
    @GET("posts")
    Call<List<Post>> getPosts();
}
