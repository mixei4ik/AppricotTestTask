package com.example.appricottesttask.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.appricottesttask.R
import com.example.appricottesttask.databinding.FragmentDetailCharacterBinding
import com.example.appricottesttask.presentation.RickAndMortyViewModel
import com.example.sevenwindsstudiotask.common.Resource

class DetailCharacterFragment: Fragment(R.layout.fragment_detail_character) {

    private lateinit var binding: FragmentDetailCharacterBinding

    private lateinit var rickAndMortyViewModel: RickAndMortyViewModel

    private val args: DetailCharacterFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailCharacterBinding.bind(view)

        rickAndMortyViewModel = (activity as MainActivity).rickAndMortyViewModel

        val id = args.id

        rickAndMortyViewModel.getDetailCharacter(id)

        rickAndMortyViewModel.detailCharacters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let { character ->
                        with(binding) {
                            detailNameText.text = character.name
                            detailStatusText.text = character.status
                            detailSpeciesText.text = character.species
                            detailLocationText.text = character.location
                            detailImageView.load(character.image) {
                                placeholder(R.drawable.ic_image)
                                error(R.drawable.ic_image)
                                crossfade(true)
                            }
                            detailGenderText.text = character.gender
                            detailAmountEpisodeText.text = character.amountEpisode.toString()
                        }
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
