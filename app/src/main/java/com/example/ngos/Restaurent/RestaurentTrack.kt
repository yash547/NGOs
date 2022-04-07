package com.example.ngos.Restaurent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ngos.MainActivity
import com.example.ngos.NGOS.NgosAddItem
import com.example.ngos.NGOS.NgosViewList
import com.example.ngos.NGOS.Ngosprofile
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth

class RestaurentTrack : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurent_track)


        val ResTAddfood=findViewById<ImageView>(R.id.RestaurenttractimageView28)
        val ResTProfile=findViewById<ImageView>(R.id.RestaurenttractimageView26)
        val ResTViewlist=findViewById<ImageView>(R.id.RestaurenttractimageView30)
        val ResTlogout=findViewById<ImageView>(R.id.RestaurenttractimageView31)

        // connecting the kotlin file to another kotlin file for bottom navigation
        ResTAddfood.setOnClickListener {
            val intent= Intent(this,  RestaurentADDItem::class.java )
            startActivity(intent)
        }
        ResTProfile.setOnClickListener {
            val intent= Intent(this,  RestaurentProfile::class.java )
            startActivity(intent)
        }
        ResTViewlist.setOnClickListener {
            val intent= Intent(this,  RestaurentViewList::class.java )
            startActivity(intent)
        }
        ResTlogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}