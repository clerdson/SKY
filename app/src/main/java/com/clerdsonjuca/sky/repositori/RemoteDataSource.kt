package com.clerdsonjuca.sky.repositori

import com.clerdsonjuca.sky.api.SimpleApi
import javax.inject.Inject

 public class RemoteDataSource @Inject constructor( val simpleApi: SimpleApi) {
    suspend fun getAll() =simpleApi.getAll()
}