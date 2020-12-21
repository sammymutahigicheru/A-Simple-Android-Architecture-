package com.sammy.androidarchitecture.ui.character_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.sammy.androidarchitecture.R
import com.sammy.androidarchitecture.commons.Resource
import com.sammy.androidarchitecture.commons.autoCleared
import com.sammy.androidarchitecture.data.model.Result
import com.sammy.androidarchitecture.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private var binding:FragmentCharacterDetailsBinding by autoCleared()
    private val viewModel:CharacterDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_character_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.setId(id) }
        setObservers()
    }

    private fun setObservers() {
        viewModel.character.observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.characterCl.visibility = View.VISIBLE
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }
    private fun bindCharacter(character: Result) {
        binding.name.text = character.name
        binding.species.text = character.species
        binding.status.text = character.status
        binding.gender.text = character.gender
        Glide.with(binding.root)
            .load(character.image)
            .transform(CircleCrop())
            .into(binding.image)
    }
}