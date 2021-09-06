package com.yaromchikv.ecars.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yaromchikv.ecars.R
import com.yaromchikv.ecars.data.Car
import com.yaromchikv.ecars.data.CarViewModel
import com.yaromchikv.ecars.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var carViewModel: CarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val name = binding.addModelText.editText?.text.toString()
        val acceleration = binding.addAccelerationText.editText?.text.toString().toDoubleOrNull()
        val price = binding.addPriceText.editText?.text.toString().toDoubleOrNull()

        if (name.isNotEmpty() && acceleration != null && price != null) {
            val car = Car(0, name, acceleration, price)
            carViewModel.addCar(car)
            Toast.makeText(requireContext(), "Added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}