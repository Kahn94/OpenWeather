package ru.kahn.openweather.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.kahn.openweather.*
import ru.kahn.openweather.adapter.AdapterMain
import ru.kahn.openweather.applications.AppOpenWeather
import ru.kahn.openweather.model.ModelListCity
import ru.kahn.openweather.model.ModelWeatherMain
import java.util.ArrayList

class ActivityMain : AppCompatActivity() {
    var arrayListCity: List<ModelListCity> = ArrayList()
    var adapter: AdapterMain =
        AdapterMain(arrayListCity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        rv_main_list_city.setLayoutManager(layoutManager)
        rv_main_list_city.setAdapter(adapter)

        bt_send.setOnClickListener {
            if (isCorrectCoordinates) {
                responseWeather()
            }
        }
    }

    private fun responseWeather() {
        val latitude = parsDouble(et_latitude.text.toString())
        val longitude = parsDouble(et_longitude.text.toString())
        val key = resources.getString(R.string.key)

        val call: Call<ModelWeatherMain> = AppOpenWeather.apiInterface.getFindWheather(latitude, longitude, 10, "ru", "metric", key)
        call.enqueue(object: Callback<ModelWeatherMain> {
            override fun onResponse(call: Call<ModelWeatherMain>, response: Response<ModelWeatherMain>) {
                if(response.body()?.cod.equals("200")) {
                    adapter.arrayListCity = response.body()!!.list
                    adapter.notifyDataSetChanged()
                    rl_no_data.visibility = View.GONE
                    rv_main_list_city.visibility = View.VISIBLE
                } else {
                    rl_no_data.visibility = View.VISIBLE
                    rv_main_list_city.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ModelWeatherMain>, t: Throwable) {
                Log.e("ОШИБКА", t.toString())
            }
        })
    }

    private fun parsDouble(str: String): Double {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            -91.00
        }
    }

    private val isCorrectCoordinates: Boolean
        get() {
            val latitude = parsDouble(et_latitude.text.toString())
            val longitude = parsDouble(et_longitude.text.toString())
            return if (latitude < -90 || latitude > 90) {
                et_latitude.setError(resources.getString(R.string.latitude_error))
                false
            } else if (longitude < -90 || longitude > 90) {
                et_longitude.setError(resources.getString(R.string.longitude_error))
                false
            } else {
                true
            }
        }
}