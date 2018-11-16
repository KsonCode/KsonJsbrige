package com.example.kson.ksonjsbrige;

import com.example.kson.ksonjsbrige.entity.LoginEntity;
import com.example.kson.ksonjsbrige.entity.AdEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginEntity> login(@FieldMap HashMap<String,String> params);

    @GET("ad/getAd")
    Observable<AdEntity> getAd();
}
