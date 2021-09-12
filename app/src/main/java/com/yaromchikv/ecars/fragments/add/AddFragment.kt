package com.yaromchikv.ecars.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yaromchikv.ecars.databinding.FragmentAddBinding
import com.yaromchikv.ecars.getRandomImage
import com.yaromchikv.ecars.model.Car
import com.yaromchikv.ecars.viewmodel.CarViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private lateinit var carViewModel: CarViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val name = binding.addNameText.editText?.text.toString()
            .replace("\\s+".toRegex(), " ").trim()
        val acceleration = binding.addAccelerationText.editText?.text.toString().toDoubleOrNull()
        val range = binding.addRangeText.editText?.text.toString().toIntOrNull()
        val price = binding.addPriceText.editText?.text.toString().toIntOrNull()

        when {
            name.isEmpty() ->
                Toast.makeText(requireContext(), "Please enter the model of the E-Car", Toast.LENGTH_SHORT).show()
            acceleration == null ->
                Toast.makeText(requireContext(), "Please enter the acceleration of the E-Car", Toast.LENGTH_SHORT).show()
            range == null ->
                Toast.makeText(requireContext(), "Please enter the range of the E-Car", Toast.LENGTH_SHORT).show()
            price == null ->
                Toast.makeText(requireContext(), "Please enter the price of the E-Car", Toast.LENGTH_SHORT).show()
            else -> {
                val car = Car(0, name, acceleration, range, price, getRandomImage())
                carViewModel.addCar(car)
                Toast.makeText(requireContext(), "$name successfully added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
            }
        }

    }
}