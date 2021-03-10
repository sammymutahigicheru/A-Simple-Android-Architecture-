package com.sammy.androidarchitecture.data.mappers

import com.sammy.androidarchitecture.network.model.Character
import com.sammy.androidarchitecture.network.model.Result

internal fun Result.toCharcters(): Character =
    Character(this.created,this.episode,this.gender,this.id,this.image,this.location,this.name,this.origin,this.species,this.status,this.type,this.url)