package com.example.ngos.NGOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth

class Ngosprofile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngosprofile)

        val ngoSpAddfood=findViewById<ImageView>(R.id.ngosprofileimageView28)
        val ngoSpTrack=findViewById<ImageView>(R.id.ngosprofileimageView29)
        val ngoSpViewlist=findViewById<ImageView>(R.id.ngosprofileimageView30)
        val ngoSplogout=findViewById<ImageView>(R.id.ngosprofileimageView31)

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
            val intent= Intent(this, NgosRegister::class.java)
            startActivity(intent)
        }



    }
}