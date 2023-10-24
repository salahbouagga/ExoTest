package com.example.exotest.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exotest.R
import com.example.exotest.data.model.WeatherData
import com.example.exotest.ui.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso


class WeatherAdapter(val viewModel:WeatherViewModel , val myDataset: ArrayList<WeatherData>, context: Context) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>(){

    var con = context
    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        var icon : ImageView
        var region : TextView? = null
        var temperature : TextView? = null
        var clouds : TextView? = null

        init {
            this.icon = row.findViewById(R.id.weather_icon)
            this.region = row.findViewById(R.id.ville)
            this.temperature = row.findViewById(R.id.temperature)
            this.clouds = row.findViewById(R.id.clouds)
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val weather = myDataset?.get(position)
        val data =
            "https://openweathermap.org/img/wn/${weather?.weather?.get(0)?.icon}@2x.png"
        Picasso.with(con)
            .load(data)
            .into(holder.icon);
        holder.region?.text = weather?.region
        holder.clouds?.text = weather?.weather?.get(0)?.description
        holder.temperature?.text = weather?.main?.temperature?.take(2)+"°"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.weather_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }



}