package com.hardi.cakelist.data.repository

import android.util.Log
import com.hardi.cakelist.data.api.NetworkService
import com.hardi.cakelist.data.model.Cake
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CakeListRepository @Inject constructor(private val networkService: NetworkService) {

    fun getCakeList(): Flow<List<Cake>> {
        return flow {
            emit(networkService.getCakeList())
            Log.d("before filter", "$networkService.getCakeList()")
        }.map {
            it.sortedBy { item -> item.title }
        }
    }

}