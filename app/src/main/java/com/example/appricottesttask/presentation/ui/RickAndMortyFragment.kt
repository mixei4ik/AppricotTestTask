package com.example.appricottesttask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appricottesttask.R
import com.example.appricottesttask.databinding.FragmentRickAndMortyBinding
import com.example.appricottesttask.presentation.RickAndMortyAdapter
import com.example.appricottesttask.presentation.RickAndMortyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RickAndMortyFragment : Fragment(R.layout.fragment_rick_and_morty) {

    private lateinit var binding: FragmentRickAndMortyBinding
    private lateinit var rickAndMortyAdapter: RickAndMortyAdapter
    private lateinit var rickAndMortyViewModel: RickAndMortyViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRickAndMortyBinding.bind(view)

        rickAndMortyViewModel = (activity as MainActivity).rickAndMortyViewModel
        rickAndMortyAdapter = RickAndMortyAdapter()

        setupRecyclerView()

        lifecycleScope.launch {
            rickAndMortyViewModel.rickAndMortyCharacters.collectLatest { pagingData ->
                rickAndMortyAdapter.submitData(pagingData)
            }
        }

        rickAndMortyAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putInt("id", it.id)
            }
            findNavController().navigate(
                R.id.action_rickAndMortyFragment_to_detailCharacterFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        binding.rickAndMortyRecyclerview.apply {
            adapter = rickAndMortyAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}