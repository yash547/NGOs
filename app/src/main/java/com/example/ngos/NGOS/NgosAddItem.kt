package com.example.ngos.NGOS

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.random.Random

class NgosAddItem : AppCompatActivity() {

  //  private lateinit var NGOSREauth: FirebaseAuth
    var user: FirebaseUser? = null
    private  lateinit var NgoDatabaseReference : DatabaseReference

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


        // connecting the firebase realtime database data to XML ngosAdditem data.

        user= FirebaseAuth.getInstance().currentUser
        NgoDatabaseReference = FirebaseDatabase.getInstance().getReference("NGOS_ADDFOOD_Items")
        val  userID= user?.uid;



        // connecting the xml to kotlin variable to insert the add food, feecount ,Date time,Status.
        val ngosaddfoods=findViewById<EditText>(R.id.ngosfoodadd)
        val ngosaddfeed=findViewById<EditText>(R.id.ngosfeedcount)
        val addfoodfeedbtn=findViewById<Button>(R.id.Ngoaddsubmitbtn)
        val status="pending"
        lateinit var date:String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            date= LocalDate.now().toString()
            date.format(DateTimeFormatter.ofPattern("d/M/y"))

        }


        // Addfood button code listeners

        addfoodfeedbtn.setOnClickListener {

            val afood=ngosaddfoods.text.toString()
            val afeed=ngosaddfeed.text.toString()
            val uj :String =userID.toString()

            // fetch the the current time
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss z")
            val currentTime: String = simpleDateFormat.format(Date())

            val wz= NgosAddfood(afood,afeed,date,currentTime,uj,status)
            val myRandomValues = Random.nextInt(0,967897825)

            if (uj != null) {

                NgoDatabaseReference .child(myRandomValues.toString()).setValue(wz).addOnSuccessListener {

                    Toast.makeText(this, "Food ADD", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, NgosAddItem::class.java)
                    startActivity(intent)


                }.addOnFailureListener {
                    Toast.makeText(this, "sorry server issue", Toast.LENGTH_SHORT).show()
                }
            }



        }




    }
}