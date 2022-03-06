package com.example.ngos.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ngos.R
import com.example.ngos.Restaurent.RestaurentRegister

class UsersLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_login)

        val sinups=findViewById<ImageView>(R.id.UserSSSignup)





        sinups.setOnClickListener {
            val intent= Intent(this, UsersRegister::class.java)
            startActivity(intent)
        }



    }
}