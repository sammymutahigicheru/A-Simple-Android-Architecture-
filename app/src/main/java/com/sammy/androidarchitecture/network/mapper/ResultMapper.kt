package com.sammy.androidarchitecture.network.mapper

import com.sammy.androidarchitecture.domain.util.DomainMapper
import com.sammy.androidarchitecture.domain.model.Character
import com.sammy.androidarchitecture.network.model.Result

class ResultMapper: DomainMapper<Result, Character> {
    override fun mapToDomainModel(model: Result) =
        Character(
            model.image,
            model.name,
            model.species,
            model.status
        )

    fun toDomainList(initial:List<Result>):List<Character>{
        return initial.map { mapToDomainModel(it) }
    }
}