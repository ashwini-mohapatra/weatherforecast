package com.ashwini.weatherforecast

import APIModel0
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI{

    @GET("weather")
    fun getForecast(@Query("q") q: String?,@Query("appid") appid:String?) : Call<APIModel0>
}
