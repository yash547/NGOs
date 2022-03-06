package com.example.ngos.NGOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth

class NgosRegister : AppCompatActivity() {

    private lateinit var NGOSREauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngos_register)

        val sinip=findViewById<ImageView>(R.id.imageView12)

        val btnreg=findViewById<Button>(R.id.Ngoregbutton)

        val ngosregname=findViewById<EditText>(R.id.ngoregName)
        val ngosregemail=findViewById<EditText>(R.id.ngoregemail)
        val ngosregpass=findViewById<EditText>(R.id.ngoregpass)
val progress=findViewById<ProgressBar>(R.id.progressBar2)


        sinip.setOnClickListener {
            val intent= Intent(this, NgosLogin::class.java)
            startActivity(intent)
        }


        // firebase connection
        NGOSREauth  = FirebaseAuth.getInstance()

        // maintain the session
        if( NGOSREauth .currentUser!=null)
        {
            val intent= Intent(this,NgosAddItem::class.java)
            startActivity(intent)
        }

// firebase registration process start

        btnreg.setOnClickListener {

            val ngoNameR=ngosregname.text.toString()
            val ngoemailR=ngosregemail.text.toString()
            val ngosPasswordR=ngosregpass.text.toString()

            progress.visibility= View.VISIBLE

            NGOSREauth .createUserWithEmailAndPassword(ngoemailR,ngosPasswordR).addOnCompleteListener {

                if(it.isSuccessful)
                {
                    Toast.makeText(this, "ngo user created", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this, NgosAddItem::class.java)
                    startActivity(intent)


                }


            }




        }


    }
}