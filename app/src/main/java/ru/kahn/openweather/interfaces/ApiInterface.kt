package ru.kahn.openweather.interfaces

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kahn.openweather.model.ModelWeatherMain

interface ApiInterface {

    @GET("find")
    fun getFindWheather(@Query("lat") lat: Double,
                        @Query("lon") lon: Double,
                        @Query("cnt") cnt: Int,
                        @Query("lang") lang: String,
                        @Query("units") units: String,
                        @Query("appid") appid: String): Call<ModelWeatherMain>
}