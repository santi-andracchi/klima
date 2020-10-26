package com.testapp.klima.mvvm.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.testapp.klima.R
import com.testapp.klima.model.ForecastData

class ForecastAdapter(private val forecastDataList: List<ForecastData>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_forecast, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.description.text = forecastDataList[position].description
        holder.mainDescription.text = forecastDataList[position].main_description
        holder.pressure.text = "p: " + forecastDataList[position].pressure.toString() + " hPa"
        holder.humidity.text = "h: " + forecastDataList[position].humidity.toString() + "%"
        holder.day.text = forecastDataList[position].day
        Picasso.get().load("http://openweathermap.org/img/wn/"+ forecastDataList[position].icon + "@2x.png").into(holder.icon)
        if (forecastDataList[position].currentTemp == 0.0) {
            holder.temperature.text = "t: " + forecastDataList[position].tempMin?.toInt() + "/" + forecastDataList[position].tempMax?.toInt() + " C"
        } else {
            holder.temperature.text = "t: " + forecastDataList[position].currentTemp.toString() + " C"
        }
    }

    override fun getItemCount(): Int {
        return forecastDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var description: TextView
        var mainDescription: TextView
        var pressure: TextView
        var temperature: TextView
        var humidity: TextView
        var day: TextView
        var icon: ImageView

        init {
            description = itemView.findViewById(R.id.description)
            mainDescription = itemView.findViewById(R.id.main_description)
            pressure = itemView.findViewById(R.id.pressure)
            temperature = itemView.findViewById(R.id.temperature)
            humidity = itemView.findViewById(R.id.humidity)
            day = itemView.findViewById(R.id.day)
            icon = itemView.findViewById(R.id.icon)
        }
    }
}