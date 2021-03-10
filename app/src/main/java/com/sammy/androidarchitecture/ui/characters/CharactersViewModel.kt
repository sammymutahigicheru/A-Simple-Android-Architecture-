package com.sammy.androidarchitecture.ui.characters

import android.net.ConnectivityManager
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sammy.androidarchitecture.data.repository.CharacterRepository
import com.sammy.androidarchitecture.domain.model.Character
import com.sammy.androidarchitecture.interactors.characters_list.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val connectivityManager: com.sammy.androidarchitecture.commons.ConnectivityManager
):ViewModel(){
    val characters:MutableLiveData<List<Character>> = MutableLiveData()
    val loading:MutableLiveData<Boolean> = MutableLiveData()

    private fun getCharacters(){
        getCharacters.execute(connectivityManager.isNetworkAvailable.value!!).onEach {dataState ->
            loading.value = dataState.loading
            dataState.data?.let { list ->
                characters.value = list
            }
            dataState.error?.let {
                Timber.e(it)
            }
        }.launchIn(viewModelScope)
    }
}