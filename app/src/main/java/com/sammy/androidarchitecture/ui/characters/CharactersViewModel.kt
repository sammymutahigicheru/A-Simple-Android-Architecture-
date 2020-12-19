package com.sammy.androidarchitecture.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sammy.androidarchitecture.data.repository.CharacterRepository

class CharactersViewModel @ViewModelInject constructor(
    private val characterRepository: CharacterRepository
):ViewModel(){
    val characters = characterRepository.getCharacters()
}