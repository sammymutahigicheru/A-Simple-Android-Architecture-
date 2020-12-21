package com.sammy.androidarchitecture.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sammy.androidarchitecture.R
import com.sammy.androidarchitecture.commons.Resource
import com.sammy.androidarchitecture.commons.autoCleared
import com.sammy.androidarchitecture.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_characters.*

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var binding:FragmentCharactersBinding by autoCleared()
    private val viewModel:CharactersViewModel by viewModels()
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
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.characters.observe(viewLifecycleOwner, {
           when(it.status){
               Resource.Status.SUCCESS -> {
                   progressBar.visibility = View.GONE
                   if (!it.data!!.results.isNullOrEmpty()) characters.text = it.data.results.toString()
               }
               Resource.Status.ERROR ->
                   Log.e("CharactersFragment",it.message!!)
               Resource.Status.LOADING ->
                   progressBar.visibility = View.VISIBLE
           }
        })
    }

}