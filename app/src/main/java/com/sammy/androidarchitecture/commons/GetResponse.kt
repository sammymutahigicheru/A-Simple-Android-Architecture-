package com.sammy.androidarchitecture.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sammy.androidarchitecture.commons.Resource.Status
import com.sammy.androidarchitecture.commons.Resource.Status.*
import kotlinx.coroutines.Dispatchers

fun <A,T> performGetOperation(
        networkCall: suspend () -> Resource<A>,
        saveCallResult: suspend (A) -> Unit
):LiveData<Resource<T>> =
        liveData(Dispatchers.IO){
            val response = networkCall.invoke()
            if(response.status == SUCCESS){
                saveCallResult(response.data!!)
            }else if (response.status == ERROR){
                emit(Resource.error(response.message!!))
            }
        }