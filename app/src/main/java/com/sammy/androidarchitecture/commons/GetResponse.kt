package com.sammy.androidarchitecture.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sammy.androidarchitecture.commons.Resource.Status
import com.sammy.androidarchitecture.commons.Resource.Status.*
import kotlinx.coroutines.Dispatchers

fun <T> performGetOperation(
        networkCall: suspend () -> Resource<T>
):LiveData<Resource<T>> =
        liveData(Dispatchers.IO){
            val response = networkCall.invoke()
            if(response.status == SUCCESS){
                emit(Resource.success(response.data!!))
                //do db insertion
            }else if (response.status == ERROR){
                emit(Resource.error(response.message!!))
            }
        }