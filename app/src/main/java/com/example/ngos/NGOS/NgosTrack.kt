package com.example.ngos.NGOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth

class NgosTrack : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngos_track)

        val ngoTAddfood=findViewById<ImageView>(R.id.ngostractimageView28)
        val ngoTProfile=findViewById<ImageView>(R.id.ngostractimageView26)
        val ngoTViewlist=findViewById<ImageView>(R.id.ngostractimageView30)
        val ngoTlogout=findViewById<ImageView>(R.id.ngostractimageView31)

        // connecting the kotlin file to another kotlin file for bottom navigation
        ngoTAddfood.setOnClickListener {
            val intent= Intent(this, NgosAddItem::class.java )
            startActivity(intent)
        }
        ngoTProfile.setOnClickListener {
            val intent= Intent(this, Ngosprofile::class.java )
            startActivity(intent)
        }
        ngoTViewlist.setOnClickListener {
            val intent= Intent(this, NgosViewList::class.java )
            startActivity(intent)
        }
        ngoTlogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, NgosRegister::class.java)
            startActivity(intent)
        }

    }
}