package com.sammy.androidarchitecture.domain.util

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model:T):DomainModel
}