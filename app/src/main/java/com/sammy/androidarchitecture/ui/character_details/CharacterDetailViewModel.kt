package com.sammy.androidarchitecture.ui.character_details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.sammy.androidarchitecture.commons.Resource
import com.sammy.androidarchitecture.data.model.Result
import com.sammy.androidarchitecture.data.repository.CharacterRepository

class CharacterDetailViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
):ViewModel(){
    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        Log.e("Id","****Id**=>${id}")
        repository.getCharacterById(id)
    }
    val character: LiveData<Resource<Result>> = _character


    fun start(id: Int) {
        _id.value = id
    }
}