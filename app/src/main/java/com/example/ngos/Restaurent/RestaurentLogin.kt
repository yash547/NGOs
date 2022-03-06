package com.example.ngos.Restaurent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ngos.NGOS.NgosAddItem
import com.example.ngos.NGOS.NgosRegister
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth

class RestaurentLogin : AppCompatActivity() {

    private lateinit var restaurentlogssauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurent_login)
        val sinup=findViewById<ImageView>(R.id.ResSignup)


        val Restaurentlogingemail=findViewById<EditText>(R.id.ResEmailLogin)
        val Restaurentloginpass=findViewById<EditText>(R.id.ResPassLogin)
        val Restaurentloggs=findViewById<Button>(R.id.buttonResLOGIN)

        val progressRestaurentlog=findViewById<ProgressBar>(R.id.progressBar4)


        sinup.setOnClickListener {
            val intent= Intent(this, RestaurentRegister::class.java)
            startActivity(intent)
        }


        // firebase connection
        restaurentlogssauth= FirebaseAuth.getInstance()

        // maintain the session
        if(  restaurentlogssauth.currentUser!=null)
        {
            val intent= Intent(this, RestaurentADDItem::class.java)
            startActivity(intent)
        }


        // firebase login
        Restaurentloggs.setOnClickListener {

            val ngoslogemail=Restaurentlogingemail.text.toString()
            val ngoslogPas=Restaurentloginpass.text.toString()

            progressRestaurentlog.visibility= View.VISIBLE

            restaurentlogssauth.signInWithEmailAndPassword(ngoslogemail,ngoslogPas).addOnCompleteListener {

                if(it.isSuccessful)
                {
                    Toast.makeText(this, "Restaurent login in", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, RestaurentADDItem::class.java)
                    startActivity(intent)


                }


            }


        }

    }
}