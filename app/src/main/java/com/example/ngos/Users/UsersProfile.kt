package com.example.ngos.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ngos.MainActivity
import com.example.ngos.R
import com.example.ngos.Restaurent.RestaurentADDItem
import com.example.ngos.Restaurent.RestaurentTrack
import com.example.ngos.Restaurent.RestaurentViewList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UsersProfile : AppCompatActivity() {
    var reference: DatabaseReference? = null
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_profile)


        val UsersSpAddfood=findViewById<ImageView>(R.id.UsersPROimageView28)
        val UsersSpTrack=findViewById<ImageView>(R.id.UsersPROimageView29)
        val UsersSpViewlist=findViewById<ImageView>(R.id.UsersPROimageView30)
        val UsersSplogout=findViewById<ImageView>(R.id.UsersPROimageView31)
// connecting profile data to kotlin variable.
        val Usersprofilename=findViewById<TextView>(R.id.UsersPROprofileName)
        val Usersprofileemail=findViewById<TextView>(R.id.UsersPROemail)


        // connecting the kotlin file to another kotlin file for bottom navigation
        UsersSpAddfood.setOnClickListener {
            val intent= Intent(this,  UsersADDItem::class.java )
            startActivity(intent)
        }
        UsersSpTrack.setOnClickListener {
            val intent= Intent(this, UsersTrack::class.java )
            startActivity(intent)
        }
        UsersSpViewlist.setOnClickListener {
            val intent= Intent(this, UsersViewList::class.java )
            startActivity(intent)
        }

        UsersSplogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        // connecting the firebase realtime database data to XML resprofile data.

        user= FirebaseAuth.getInstance().currentUser
        reference= FirebaseDatabase.getInstance().getReference("INDIVIDUALUSERSDETAILS");
        val  userID= user?.uid;


        userID?.let {
            reference!!.child(it).get().addOnSuccessListener {

                if (it.exists()){

                    val fullname = it.child("indname").value
                    val emails = it.child("indemailid").value

                     Usersprofilename.text= fullname.toString()
                    Usersprofileemail.text= emails.toString()


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