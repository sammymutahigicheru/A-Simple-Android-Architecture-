package com.sammy.androidarchitecture.network.response

import com.sammy.androidarchitecture.network.model.Info
import com.sammy.androidarchitecture.network.model.Result

data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)