package com.example.ngos.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.ngos.R
import com.example.ngos.Restaurent.RestaurentADDItem
import com.example.ngos.Restaurent.RestaurentLogin
import com.google.firebase.auth.FirebaseAuth

class UsersRegister : AppCompatActivity() {

    private lateinit var UsersREauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_register)

        val sinip=findViewById<ImageView>(R.id.UsersSignin)

        val Usersbtnreg=findViewById<Button>(R.id.Usersregbutton)


        val Usersregname=findViewById<EditText>(R.id.UsersregName)
        val Usersregemail=findViewById<EditText>(R.id.Usersregemail)
        val Usersregpass=findViewById<EditText>(R.id.Usersregpass)



        sinip.setOnClickListener {
            val intent= Intent(this, UsersLogin::class.java)
            startActivity(intent)
        }

        Usersbtnreg.setOnClickListener {
            val intent= Intent(this, UsersADDItem::class.java)
            startActivity(intent)


        }

        // firebase connection
        UsersREauth  = FirebaseAuth.getInstance()

        // maintain the session
        if( UsersREauth .currentUser!=null)
        {
            val intent= Intent(this, UsersADDItem::class.java)
            startActivity(intent)
        }



// firebase registration process start






    }
}