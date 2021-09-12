package com.yaromchikv.ecars.fragments.list

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaromchikv.ecars.R
import com.yaromchikv.ecars.databinding.FragmentListBinding
import com.yaromchikv.ecars.viewmodel.CarViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private lateinit var carViewModel: CarViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val listAdapter = ListAdapter()
        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        if (carViewModel.prefs.getBoolean("use_room", true)) {
            carViewModel.getDataUsingRoom().observe(viewLifecycleOwner, { newListOfCar ->
                listAdapter.setData(newListOfCar)
                binding.emptyBox.isVisible = listAdapter.itemCount == 0
                binding.emptyText.isVisible = listAdapter.itemCount == 0
            })
        } else {
            listAdapter.setData(carViewModel.getDataUsingCursors())
            binding.emptyBox.isVisible = listAdapter.itemCount == 0
            binding.emptyText.isVisible = listAdapter.itemCount == 0
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings) {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToSettingsFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}