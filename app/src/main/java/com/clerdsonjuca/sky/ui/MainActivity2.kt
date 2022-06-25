package com.clerdsonjuca.sky.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.clerdsonjuca.sky.R
import com.clerdsonjuca.sky.databinding.ActivityMain2Binding
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var _binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(_binding.root)

        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        _binding.imageView.load(image)
        _binding.textView2.text = name
    }
}