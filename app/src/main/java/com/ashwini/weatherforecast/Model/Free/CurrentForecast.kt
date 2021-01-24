package com.ashwini.weatherforecast.Model.Free

import Coord
import Weather
import com.google.gson.annotations.SerializedName

data class CurrentForecast(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val weather: ArrayList<Weather>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val main: Mains,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt:Long,
    @SerializedName("sys")
    val sys:Sys,
    @SerializedName("timezone")
    val timezone:Int,
    @SerializedName("id")
    val id:Long,
    @SerializedName("name")
    val name:String,
    @SerializedName("cod")
    val cod:Int
    )
