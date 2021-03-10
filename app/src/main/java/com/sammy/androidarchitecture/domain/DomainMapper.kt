package com.sammy.androidarchitecture.domain

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model:T):DomainModel
    fun mapFromDomainModel(domainModel: DomainModel):T
}