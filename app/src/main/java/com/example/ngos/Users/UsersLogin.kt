package com.example.ngos.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ngos.R
import com.example.ngos.Restaurent.RestaurentADDItem
import com.example.ngos.Restaurent.RestaurentRegister
import com.google.firebase.auth.FirebaseAuth

class UsersLogin : AppCompatActivity() {

    private lateinit var  Userlogssauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_login)


        val Userlogingemail=findViewById<EditText>(R.id.UserEmailLogin)
        val Userloginpass=findViewById<EditText>(R.id.UserPassLogin)
        val USERRloggs=findViewById<Button>(R.id.buttonUserLOGIN)

        val sinups=findViewById<ImageView>(R.id.UserSSSignup)


        val progressUserlog=findViewById<ProgressBar>(R.id.progressBar42)




        sinups.setOnClickListener {

            val intent= Intent(this, UsersRegister::class.java)
            startActivity(intent)
        }


        // firebase connection
        Userlogssauth= FirebaseAuth.getInstance()

        // maintain the session
        if(  Userlogssauth.currentUser!=null)
        {
            val intent= Intent(this,  UsersADDItem::class.java)
            startActivity(intent)
        }


        // firebase login
        USERRloggs.setOnClickListener {

            val  Userrlogemail= Userlogingemail.text.toString()
            val  UserrlogPas= Userloginpass.text.toString()

            progressUserlog.visibility= View.VISIBLE

            Userlogssauth.signInWithEmailAndPassword( Userrlogemail, UserrlogPas).addOnCompleteListener {

                if(it.isSuccessful)
                {
                    Toast.makeText(this, "Individual User login in", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, UsersRegister::class.java)
                    startActivity(intent)

                }


            }


        }

    }
}