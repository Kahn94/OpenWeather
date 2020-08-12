package ru.kahn.openweather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_city.view.*
import ru.kahn.openweather.model.ModelListCity
import ru.kahn.openweather.R

class AdapterMain(var arrayListCity: MutableList<ModelListCity>, var click: ClickListener) : RecyclerView.Adapter<AdapterMain.ViewHolder>() {

    interface ClickListener {
        fun onLongClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_city, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayListCity.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ModelListCity = arrayListCity[position]
        val imagePath = "http://openweathermap.org/img/wn/${model.weather[0].icon}@4x.png"
        Picasso.with(holder.itemView.context)
            .load(imagePath)
            .fit()
            .into(holder.itemView.image_item_weather)
        holder.itemView.tv_item_gradus.setText("${model.main.temp}\u2103")
        holder.itemView.tv_item_city.setText(model.name)
        holder.itemView.setOnLongClickListener {
            click.onLongClick(position)
            false
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}