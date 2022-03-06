package com.example.ngos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ngos.NGOS.NgosLogin
import com.example.ngos.Restaurent.RestaurentLogin
import com.example.ngos.Users.UsersLogin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

   val ngos=findViewById<ImageView>(R.id.imageView3)
        val restaurent=findViewById<ImageView>(R.id.imageView4)
        val users=findViewById<ImageView>(R.id.imageView5)

        ngos.setOnClickListener {

            val intent=Intent(this, NgosLogin::class.java)
            startActivity(intent)

        }


        restaurent.setOnClickListener {

            val intent=Intent(this, RestaurentLogin::class.java)
            startActivity(intent)

        }

        users.setOnClickListener {


            val intent=Intent(this, UsersLogin::class.java)
            startActivity(intent)

        }



    }
}