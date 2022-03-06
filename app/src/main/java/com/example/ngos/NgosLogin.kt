package com.example.ngos

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class NgosLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngos_login)
    val sinup=findViewById<ImageView>(R.id.imageView10)

        sinup.setOnClickListener {
    val intent= Intent(this,NgosRegister::class.java)
            startActivity(intent)
        }

    }
}