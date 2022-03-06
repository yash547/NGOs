package com.example.ngos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class NgosRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngos_register)
        val sinip=findViewById<ImageView>(R.id.imageView12)
        val btnreg=findViewById<Button>(R.id.Ngoregbutton)
        sinip.setOnClickListener {
            val intent= Intent(this,NgosLogin::class.java)
            startActivity(intent)
        }
        btnreg.setOnClickListener {
            val intent= Intent(this,NgosAddItem::class.java)
            startActivity(intent)


        }


    }
}