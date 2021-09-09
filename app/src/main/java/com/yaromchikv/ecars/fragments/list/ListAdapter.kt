package com.yaromchikv.ecars.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yaromchikv.ecars.databinding.DataItemBinding
import com.yaromchikv.ecars.displayAcceleration
import com.yaromchikv.ecars.displayPrice
import com.yaromchikv.ecars.model.Car

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var carList = emptyList<Car>()

    class MyViewHolder(private val binding: DataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.nameText.text = car.name
            binding.accelerationText.text = car.acceleration.displayAcceleration()
            binding.priceText.text = car.price.displayPrice()
            binding.carImage.setImageResource(car.image)

            binding.cardView.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(car)
                itemView.findNavController().navigate(action)
            }
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