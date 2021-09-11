package com.yaromchikv.ecars.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yaromchikv.ecars.R
import com.yaromchikv.ecars.databinding.FragmentUpdateBinding
import com.yaromchikv.ecars.model.Car
import com.yaromchikv.ecars.viewmodel.CarViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var carViewModel: CarViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

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
            .replace("\\s+".toRegex(), " ").trim()
        val acceleration = binding.updateAccelerationText.editText?.text.toString().toDoubleOrNull()
        val price = binding.updatePriceText.editText?.text.toString().toIntOrNull()

        when {
            name.isEmpty() ->
                Toast.makeText(requireContext(), "Please enter the model of the E-Car", Toast.LENGTH_SHORT).show()
            acceleration == null ->
                Toast.makeText(requireContext(), "Please enter the acceleration of the E-Car", Toast.LENGTH_SHORT).show()
            price == null ->
                Toast.makeText(requireContext(), "Please enter the price of the E-Car", Toast.LENGTH_SHORT).show()
            else -> {
                val updatedCar =
                    Car(args.currentCar.id, name, acceleration, price, args.currentCar.image)
                carViewModel.updateCar(updatedCar)
                Toast.makeText(
                    requireContext(),
                    "${args.currentCar.name} successfully updated",
                    Toast.LENGTH_SHORT
                ).show()

                findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteCar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteCar() {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setPositiveButton("Yes") { _, _ ->
                carViewModel.deleteCar(args.currentCar)
                Toast.makeText(
                    requireContext(),
                    "${args.currentCar.name} successfully deleted",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            setNegativeButton("No") { _, _ -> }
            setTitle("Deleting ${args.currentCar.name}")
            setMessage("Do you really want to delete this E-Car?")
        }.create().show()
    }
}