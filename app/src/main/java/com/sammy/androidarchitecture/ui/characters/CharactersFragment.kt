package com.sammy.androidarchitecture.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sammy.androidarchitecture.R
import com.sammy.androidarchitecture.commons.Resource
import com.sammy.androidarchitecture.commons.autoCleared
import com.sammy.androidarchitecture.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_characters.*

@AndroidEntryPoint
class CharactersFragment : Fragment(),CharactersAdapter.CharacterItemListener {
    private var binding:FragmentCharactersBinding by autoCleared()
    private val viewModel:CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_characters, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpObservers()
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter

    }

    private fun setUpObservers() {
        viewModel.characters.observe(viewLifecycleOwner, {
           when(it.status){
               Resource.Status.SUCCESS -> {
                   binding.progressBar.visibility = View.GONE
                   if (!it.data!!.results.isNullOrEmpty()) adapter.setItems(ArrayList(it.data.results))
               }
               Resource.Status.ERROR ->
                   Log.e("CharactersFragment",it.message!!)
               Resource.Status.LOADING ->
                   binding.progressBar.visibility = View.VISIBLE
           }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailsFragment3,
            bundleOf("id" to characterId)

        )
    }

}