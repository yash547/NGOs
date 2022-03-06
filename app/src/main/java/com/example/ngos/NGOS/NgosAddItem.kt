package com.example.ngos.NGOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class NgosAddItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngos_add_item)
        val ngoSprofile =findViewById<ImageView>(R.id.imageView26)
        val ngoStrack =findViewById<ImageView>(R.id.imageView29)
        val ngoSviewlist =findViewById<ImageView>(R.id.imageView30)
        val ngoSlogouts =findViewById<ImageView>(R.id.imageView31)

// connecting the kotlin file to another kotlin file for bottom navigation
        ngoSprofile.setOnClickListener {
            val intent=Intent(this, Ngosprofile::class.java )
            startActivity(intent)
        }
        ngoStrack.setOnClickListener {
            val intent=Intent(this, NgosTrack::class.java )
            startActivity(intent)
        }
        ngoSviewlist.setOnClickListener {
            val intent=Intent(this, NgosViewList::class.java )
            startActivity(intent)
        }
        ngoSlogouts.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, NgosRegister::class.java)
            startActivity(intent)
        }






    }
}