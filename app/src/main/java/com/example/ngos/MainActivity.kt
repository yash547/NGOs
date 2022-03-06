package com.example.ngos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

   val ngos=findViewById<ImageView>(R.id.imageView3)
    ngos.setOnClickListener {

        val intent=Intent(this,NgosLogin::class.java)
        startActivity(intent)

    }

    }
}