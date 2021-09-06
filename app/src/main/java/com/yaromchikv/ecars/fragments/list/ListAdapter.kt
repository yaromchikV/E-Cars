package com.yaromchikv.ecars.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaromchikv.ecars.data.Car
import com.yaromchikv.ecars.databinding.DataItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var carList = emptyList<Car>()

    class MyViewHolder(private val binding: DataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) {
            binding.nameText.text = car.name
            binding.accelerationText.text = car.acceleration.toString()
            binding.priceText.text = car.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(carList[position])
    }

    fun setData(newCarList: List<Car>) {
        this.carList = newCarList
        notifyDataSetChanged()
    }
}