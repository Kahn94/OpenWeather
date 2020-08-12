package ru.kahn.openweather.applications

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kahn.openweather.interfaces.ApiInterface

class AppOpenWeather : Application() {
    companion object {

        var retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var apiInterface: ApiInterface = retrofit.create(
            ApiInterface:: class.java)
    }
}