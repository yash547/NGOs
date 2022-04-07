package com.example.ngos.Restaurent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ngos.MainActivity
import com.example.ngos.NGOS.NgosAddItem
import com.example.ngos.NGOS.NgosTrack
import com.example.ngos.NGOS.NgosViewList
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RestaurentProfile : AppCompatActivity() {
    var reference: DatabaseReference? = null
    var user: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurent_profile)

        val ResSpAddfood=findViewById<ImageView>(R.id.RestaurentPROimageView28)
        val ResSpTrack=findViewById<ImageView>(R.id.RestaurentPROimageView29)
        val ResSpViewlist=findViewById<ImageView>(R.id.RestaurentPROimageView30)
        val ResSplogout=findViewById<ImageView>(R.id.RestaurentPROimageView31)
// connecting profile data to kotlin variable.
        val Resprofilename=findViewById<TextView>(R.id.RestaurentPROprofileName)
        val Resprofileemail=findViewById<TextView>(R.id.RestaurentPROemail)


        // connecting the kotlin file to another kotlin file for bottom navigation
        ResSpAddfood.setOnClickListener {
            val intent= Intent(this,  RestaurentADDItem::class.java )
            startActivity(intent)
        }
        ResSpTrack.setOnClickListener {
            val intent= Intent(this,RestaurentTrack::class.java )
            startActivity(intent)
        }
        ResSpViewlist.setOnClickListener {
            val intent= Intent(this, RestaurentViewList::class.java )
            startActivity(intent)
        }

        ResSplogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // connecting the firebase realtime database data to XML resprofile data.

        user= FirebaseAuth.getInstance().currentUser
        reference= FirebaseDatabase.getInstance().getReference("RESTAURENTUSERSDETAILS");
        val  userID= user?.uid;


        userID?.let {
            reference!!.child(it).get().addOnSuccessListener {

                if (it.exists()){

                    val fullname = it.child("resname").value
                    val emails = it.child("resemailid").value

                    Resprofilename.text= fullname.toString()
                    Resprofileemail.text= emails.toString()

                    Toast.makeText(this,"Successfuly Read", Toast.LENGTH_SHORT).show()

                }else{

                    Toast.makeText(this,"User Doesn't Exist", Toast.LENGTH_SHORT).show()


                }

            }.addOnFailureListener{

                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()


            }
        }




    }
}