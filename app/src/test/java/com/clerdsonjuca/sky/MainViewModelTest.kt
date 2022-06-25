package com.clerdsonjuca.sky

import android.app.Application
import com.clerdsonjuca.sky.repositori.RemoteDataSource
import com.clerdsonjuca.sky.repositori.Repository
import com.clerdsonjuca.sky.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import javax.inject.Inject

@ExperimentalCoroutinesApi

class MainViewModelTest{


    @Mock
    private lateinit var remoteDataSource:RemoteDataSource
    private lateinit var application: Application
    private lateinit var viewModel: MainViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setup(){

        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository = Repository(remoteDataSource),application)

    }
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
    @Test
    fun `test`(){

        val value = viewModel.getAll()

        assert(value != null)

    }
}

