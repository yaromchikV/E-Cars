package com.yaromchikv.ecars.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaromchikv.ecars.R
import com.yaromchikv.ecars.databinding.FragmentListBinding
import com.yaromchikv.ecars.databinding.FragmentUpdateBinding
import com.yaromchikv.ecars.fragments.list.ListAdapter
import com.yaromchikv.ecars.model.Car
import com.yaromchikv.ecars.viewmodel.CarViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var carViewModel: CarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        binding.updateNameText.editText?.setText(args.currentCar.name)
        binding.updateAccelerationText.editText?.setText(args.currentCar.acceleration.toString())
        binding.updatePriceText.editText?.setText(args.currentCar.price.toString())

        binding.updateButton.setOnClickListener {
            updateItem()
        }
    }

    private fun updateItem() {
        val name = binding.updateNameText.editText?.text.toString()
        val acceleration = binding.updateAccelerationText.editText?.text.toString().toDoubleOrNull()
        val price = binding.updatePriceText.editText?.text.toString().toDoubleOrNull()

        if (name.isNotEmpty() && acceleration != null && price != null) {
            val updatedCar = Car(args.currentCar.id, name, acceleration, price)
            carViewModel.updateCar(updatedCar)
            Toast.makeText(requireContext(), "Update", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}