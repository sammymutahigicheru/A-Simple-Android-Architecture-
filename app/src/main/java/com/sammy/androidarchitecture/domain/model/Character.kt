package com.sammy.androidarchitecture.domain.model

import com.sammy.androidarchitecture.network.model.Location
import com.sammy.androidarchitecture.network.model.Origin

data class Character(
    val image: String,
    val name: String,
    val species: String,
    val status: String
)