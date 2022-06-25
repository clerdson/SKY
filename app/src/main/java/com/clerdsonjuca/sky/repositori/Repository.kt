package com.clerdsonjuca.sky.repositori


import android.media.CamcorderProfile.getAll
import com.clerdsonjuca.sky.api.SimpleApi
import com.clerdsonjuca.sky.model.All
import com.clerdsonjuca.sky.util.BaseApiResponse
import com.clerdsonjuca.sky.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow as Flow

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
    ) :BaseApiResponse(){

//    suspend fun getAll(): Response<List<All>> {
//        return simpleApi.getAll()
//    }

    suspend fun getDog(): Flow<NetworkResult<List<All>>> {
        return flow<NetworkResult<List<All>>> {
            emit(safeApiCall { remoteDataSource.getAll() })
        }.flowOn(Dispatchers.IO)
    }
}