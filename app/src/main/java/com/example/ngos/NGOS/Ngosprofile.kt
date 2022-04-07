package com.example.ngos.NGOS

import android.content.Intent
import android.icu.lang.UProperty.AGE
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ngos.MainActivity
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class Ngosprofile : AppCompatActivity() {
    var reference: DatabaseReference? = null
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngosprofile)

        val ngoSpAddfood=findViewById<ImageView>(R.id.ngosprofileimageView28)
        val ngoSpTrack=findViewById<ImageView>(R.id.ngosprofileimageView29)
        val ngoSpViewlist=findViewById<ImageView>(R.id.ngosprofileimageView30)
        val ngoSplogout=findViewById<ImageView>(R.id.ngosprofileimageView31)
// connecting profile data to kotlin variable.
        val NGoprofilename=findViewById<TextView>(R.id.ngoprofileName)
        val NGoprofileemail=findViewById<TextView>(R.id.ngoprofileemail)


        // connecting the kotlin file to another kotlin file for bottom navigation
        ngoSpAddfood.setOnClickListener {
            val intent= Intent(this, NgosAddItem::class.java )
            startActivity(intent)
        }
        ngoSpTrack.setOnClickListener {
            val intent= Intent(this, NgosTrack::class.java )
            startActivity(intent)
        }
        ngoSpViewlist.setOnClickListener {
            val intent= Intent(this, NgosViewList::class.java )
            startActivity(intent)
        }

        ngoSplogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // connecting the firebase realtime database data to XML ngosprofile data.

        user= FirebaseAuth.getInstance().currentUser
        reference= FirebaseDatabase.getInstance().getReference("NGOUSERSDETAILS");
       val  userID= user?.uid;


        userID?.let {
            reference!!.child(it).get().addOnSuccessListener {

                if (it.exists()){

                    val fullname = it.child("ngosname").value
                    val emails = it.child("ngosemailid").value

                    NGoprofilename.text= fullname.toString()
                   NGoprofileemail.text= emails.toString()

                    Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()

                }else{

                    Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()


                }

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }
        }


    }
}