package com.example.tesdot.network

import android.telecom.Call
import com.example.tesdot.model.Wisata
import retrofit2.http.GET


public interface ApiService {


    @GET("?action=findAll")
    fun wisata(): Call<Wisata>
}