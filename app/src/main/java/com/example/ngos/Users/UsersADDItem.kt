package com.example.ngos.Users

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.ngos.MainActivity
import com.example.ngos.NGOS.NgosAddItem
import com.example.ngos.R
import com.example.ngos.Restaurent.RestaurentAddfood
import com.example.ngos.Restaurent.RestaurentProfile
import com.example.ngos.Restaurent.RestaurentTrack
import com.example.ngos.Restaurent.RestaurentViewList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random

class UsersADDItem : AppCompatActivity() {

    var user: FirebaseUser? = null

    private  lateinit var INdividualDatabaseReference : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_additem)

        val INDSprofile =findViewById<ImageView>(R.id.UsersAprofileimageView26)
        val INDStrack =findViewById<ImageView>(R.id.UsersATrackimageView29)
        val INDSviewlist =findViewById<ImageView>(R.id.UsersAViewListimageView30)
        val INDSlogouts =findViewById<ImageView>(R.id.UsersALogoutimageView31)


// connecting the kotlin file to another kotlin file for bottom navigation
        INDSprofile.setOnClickListener {
            val intent= Intent(this, UsersProfile::class.java )
            startActivity(intent)
        }

        INDStrack.setOnClickListener {
            val intent= Intent(this, UsersTrack::class.java )
            startActivity(intent)
        }

        INDSviewlist.setOnClickListener {
            val intent= Intent(this,  UsersViewList::class.java )
            startActivity(intent)
        }

        INDSlogouts.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // connecting the firebase realtime database data to XML RestaurentAdditem data.

        user= FirebaseAuth.getInstance().currentUser
        INdividualDatabaseReference = FirebaseDatabase.getInstance().getReference("INDIVIDUAL_ADDFOOD_Items")
        val  userID= user?.uid;

        // connecting the xml to kotlin variable to insert the add food, feecount ,Date time,Status.
        val indIaddfoods=findViewById<EditText>(R.id.UsersAfoodadd)
        val indIaddfeed=findViewById<EditText>(R.id.UsersAfeedcount)
        val indIfoodfeedbtn=findViewById<Button>(R.id.UsersAaddsubmitbtn)
        val indIstatus="pending"
        lateinit var date:String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            date= LocalDate.now().toString()
            date.format(DateTimeFormatter.ofPattern("d/M/y"))

        }


        // Addfood button code listeners

        indIfoodfeedbtn.setOnClickListener {

            val INafood=indIaddfoods.text.toString()
            val INafeed=indIaddfeed.text.toString()
            val uj :String =userID.toString()

            // fetch the the current time
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss z")
            val currentTime: String = simpleDateFormat.format(Date())

            val wz= IndividualAddFood(INafood,INafeed,date,currentTime,uj,indIstatus)
            val myRandomValues = Random.nextInt(0,367897825)

            if (uj != null) {

                INdividualDatabaseReference .child(myRandomValues.toString()).setValue(wz).addOnSuccessListener {

                    Toast.makeText(this, "Individual User Food ADD", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, UsersADDItem::class.java)
                    startActivity(intent)


                }.addOnFailureListener {
                    Toast.makeText(this, "sorry server issue", Toast.LENGTH_SHORT).show()
                }
            }



        }


    }
}