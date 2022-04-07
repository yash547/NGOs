package com.example.ngos.Restaurent

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.ngos.MainActivity
import com.example.ngos.NGOS.*
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random

class RestaurentADDItem : AppCompatActivity() {

    var user: FirebaseUser? = null

    private  lateinit var RESTAURENTDatabaseReference : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurent_additem)

        val ResSprofile =findViewById<ImageView>(R.id.RestaurentAprofileimageView26)
        val ResStrack =findViewById<ImageView>(R.id.RestaurentATrackimageView29)
        val ResSviewlist =findViewById<ImageView>(R.id.RestaurentAViewListimageView30)
        val ResSlogouts =findViewById<ImageView>(R.id.RestaurentALogoutimageView31)


// connecting the kotlin file to another kotlin file for bottom navigation
        ResSprofile.setOnClickListener {
            val intent= Intent(this, RestaurentProfile::class.java )
            startActivity(intent)
        }

        ResStrack.setOnClickListener {
            val intent= Intent(this, RestaurentTrack::class.java )
            startActivity(intent)
        }

        ResSviewlist.setOnClickListener {
            val intent= Intent(this,  RestaurentViewList::class.java )
            startActivity(intent)
        }

        ResSlogouts.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // connecting the firebase realtime database data to XML RestaurentAdditem data.

        user= FirebaseAuth.getInstance().currentUser
        RESTAURENTDatabaseReference = FirebaseDatabase.getInstance().getReference("Restaurent_ADDFOOD_Items")
        val  userID= user?.uid;


        // connecting the xml to kotlin variable to insert the add food, feecount ,Date time,Status.
        val resaddfoods=findViewById<EditText>(R.id.Restaurentfoodadd)
        val ressaddfeed=findViewById<EditText>(R.id.Restaurentfeedcount)
        val resfoodfeedbtn=findViewById<Button>(R.id.Restaurentaddsubmitbtn)
        val resstatus="pending"
        lateinit var date:String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            date= LocalDate.now().toString()
            date.format(DateTimeFormatter.ofPattern("d/M/y"))

        }



        // Addfood button code listeners

        resfoodfeedbtn.setOnClickListener {

            val afood=resaddfoods.text.toString()
            val afeed=ressaddfeed.text.toString()
            val uj :String =userID.toString()

            // fetch the the current time
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss z")
            val currentTime: String = simpleDateFormat.format(Date())

            val wz= RestaurentAddfood(afood,afeed,date,currentTime,uj,resstatus)
            val myRandomValues = Random.nextInt(0,167897825)

            if (uj != null) {

                RESTAURENTDatabaseReference .child(myRandomValues.toString()).setValue(wz).addOnSuccessListener {

                    Toast.makeText(this, "Restaurent Food ADD", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, RestaurentADDItem::class.java)
                    startActivity(intent)


                }.addOnFailureListener {
                    Toast.makeText(this, "sorry server issue", Toast.LENGTH_SHORT).show()
                }
            }



        }


    }
}