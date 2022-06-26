package com.clerdsonjuca.sky.ui

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.clerdsonjuca.sky.R
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
        viewModel.response.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    it.data?.let {
                        myAdapter.setData(it)
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                    Toast.makeText(this, "Carregando", Toast.LENGTH_SHORT).show()
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

            val networkRequest = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()

            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                // network is available for use
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                }

                // Network capabilities have changed for the network
                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val unmetered =
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
                }

                // lost network connection
                override fun onLost(network: Network) {
                    super.onLost(network)

                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("Alerta.")
                    builder.setMessage("Este app precisa de internet")
                    builder.setPositiveButton("Sim") { dialog, which ->
                        Toast.makeText(this@MainActivity, "ok.", Toast.LENGTH_SHORT).show()

                    }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }

                val connectivityManager =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getSystemService(ConnectivityManager::class.java) as ConnectivityManager
                    } else {
                        TODO("VERSION.SDK_INT < M")
                    }
            connectivityManager.requestNetwork(networkRequest, networkCallback)
            }
        }

    private fun setupRecyclerview() {

        myRecyclerView.adapter = myAdapter
        myRecyclerView.layoutManager =GridLayoutManager(this,2)
    }
}
