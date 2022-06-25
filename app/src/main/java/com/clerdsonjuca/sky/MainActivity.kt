package com.clerdsonjuca.sky

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.clerdsonjuca.sky.adapter.MyAdapter
import com.clerdsonjuca.sky.util.NetworkResult
import com.clerdsonjuca.sky.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import okio.blackholeSink


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val viewModel by viewModels <MainViewModel>()
    private val myAdapter by lazy { MyAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerview()
        viewModel.getAll()
        viewModel.response.observe(this){
            when(it){
                is NetworkResult.Success->{
                    it.data?.let {
                    myAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                   Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                    Toast.makeText(this,"Carregando",Toast.LENGTH_SHORT).show()
                }
            }
//
//            if (it.isSuccessful){
//
//                it.body()?.let {
//                    myAdapter.setData(it)
//                }
//            }else{
//                println("error");
//            }
        }
    }
    private fun setupRecyclerview() {

        myRecyclerView.adapter = myAdapter
        myRecyclerView.layoutManager =GridLayoutManager(this,2)
    }
}