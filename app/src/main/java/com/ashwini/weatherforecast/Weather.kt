package com.ashwini.weatherforecast

import com.ashwini.weatherforecast.Model.Free.CurrentForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI{

    @GET("weather")
    fun getForecast(@Query("q") q: String?,@Query("appid") appid:String?) : Call<CurrentForecast>
}
