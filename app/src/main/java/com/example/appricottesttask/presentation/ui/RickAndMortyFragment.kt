package com.example.appricottesttask.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appricottesttask.R
import com.example.appricottesttask.databinding.FragmentRickAndMortyBinding
import com.example.appricottesttask.presentation.RickAndMortyAdapter
import com.example.appricottesttask.presentation.RickAndMortyViewModel
import com.example.sevenwindsstudiotask.common.Resource

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
        initRecyclerAdapter()
    }

    private fun setupRecyclerView() {
        binding.rickAndMortyRecyclerview.apply {
            adapter = rickAndMortyAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

    private fun initRecyclerAdapter() {
        rickAndMortyViewModel.rickAndMortyCharacters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let { characters ->
                        rickAndMortyAdapter.characters = characters
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}