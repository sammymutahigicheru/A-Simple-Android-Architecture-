package com.sammy.androidarchitecture.interactors.characters_list

import com.sammy.androidarchitecture.domain.data.DataState
import com.sammy.androidarchitecture.domain.model.Character
import com.sammy.androidarchitecture.network.CharactersApiService
import com.sammy.androidarchitecture.network.mapper.ResultMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacters(
    private val charactersApiService: CharactersApiService,
    private val mapper:ResultMapper
) {
    fun execute(
        isNetworkAvailable:Boolean
    ):Flow<DataState<List<Character>>>{
       return flow {
           try {
               emit(DataState.loading())
               kotlinx.coroutines.delay(1000)
               //if there is a network connection
               if (isNetworkAvailable){
                   val characters = getCharactersFromNetwork()
                   if (characters.isNotEmpty()){
                       emit(DataState.success(characters))
                   }
               }else{
                   emit(DataState.error<List<Character>>("No Internet Connection"))
               }
           }catch (e:Exception){
               emit(DataState.error<List<Character>>(e.message ?: "Unknown Error"))
           }
       }
    }
    private suspend fun getCharactersFromNetwork():List<Character>{
        return mapper.toDomainList(
            charactersApiService.getAllCharacters().results
        )
    }
}