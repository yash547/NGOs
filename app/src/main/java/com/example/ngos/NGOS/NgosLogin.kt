package com.example.ngos.NGOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth

class NgosLogin : AppCompatActivity() {

    private lateinit var Ngoslogssauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngos_login)
    val sinup=findViewById<ImageView>(R.id.imageView10)

        val ngoslogingemail=findViewById<EditText>(R.id.editTextTextPersonName)
        val ngosloginpass=findViewById<EditText>(R.id.editTextTextPersonName2)
        val ngosloggs=findViewById<Button>(R.id.Ngoslbutton)

        val progressngoslog=findViewById<ProgressBar>(R.id.progressBar3)

        sinup.setOnClickListener {
    val intent= Intent(this, NgosRegister::class.java)
            startActivity(intent)
        }

        // firebase connection
        Ngoslogssauth= FirebaseAuth.getInstance()

        // maintain the session
        if(  Ngoslogssauth.currentUser!=null)
        {
            val intent= Intent(this,NgosAddItem::class.java)
            startActivity(intent)
        }
// firebase login
        ngosloggs.setOnClickListener {

            val ngoslogemail=ngoslogingemail.text.toString()
            val ngoslogPas=ngosloginpass.text.toString()

            progressngoslog.visibility= View.VISIBLE

            Ngoslogssauth.signInWithEmailAndPassword(ngoslogemail,ngoslogPas).addOnCompleteListener {

                if(it.isSuccessful)
                {
                    Toast.makeText(this, "NGOS login in", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this, NgosAddItem::class.java)
                    startActivity(intent)


                }


            }


        }



    }
}