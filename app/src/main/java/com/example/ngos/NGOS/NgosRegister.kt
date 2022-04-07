package com.example.ngos.NGOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NgosRegister : AppCompatActivity() {

    private lateinit var NGOSREauth: FirebaseAuth

    private  lateinit var NgoDatabaseReference : DatabaseReference

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

        NgoDatabaseReference = FirebaseDatabase.getInstance().getReference("NGOUSERSDETAILS")

        // maintain the session
        if( NGOSREauth .currentUser!=null)
        {
            val intent= Intent(this,NgosAddItem::class.java)
            startActivity(intent)
        }

// firebase registration & realtime database process start

        btnreg.setOnClickListener {

            val ngoNameR=ngosregname.text.toString()
            val ngoemailR=ngosregemail.text.toString()
            val ngosPasswordR=ngosregpass.text.toString()


            progress.visibility= View.VISIBLE

            NGOSREauth .createUserWithEmailAndPassword(ngoemailR,ngosPasswordR).addOnCompleteListener {

                if(it.isSuccessful)
                {

                    val currentUser= NGOSREauth.currentUser

                    val userid= currentUser?.uid

                    val uj :String =userid.toString()
                    val w= Users(ngoemailR,ngoNameR,ngosPasswordR)

                   if (userid != null) {

                         NgoDatabaseReference .child(uj).setValue(w).addOnSuccessListener {

                            Toast.makeText(this, "ngo user created", Toast.LENGTH_SHORT).show()

                            val intent= Intent(this, NgosAddItem::class.java)
                            startActivity(intent)


                        }.addOnFailureListener {
                            Toast.makeText(this, "sorry server issue", Toast.LENGTH_SHORT).show()
                        }
                    }


                }




            }




        }


    }
}