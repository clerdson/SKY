package com.clerdsonjuca.sky.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.clerdsonjuca.sky.databinding.ActivityMain2Binding
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var _binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(_binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        _binding.imageView.load(image)
        _binding.textView2.text = name

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}