package com.example.ngos.Users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ngos.R
import com.example.ngos.Restaurent.ResUsers
import com.example.ngos.Restaurent.RestaurentADDItem
import com.example.ngos.Restaurent.RestaurentLogin
import com.example.ngos.Users.UsersADDItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UsersRegister : AppCompatActivity() {


    private  lateinit var IndividualDatabaseReference : DatabaseReference
    private lateinit var UsersaREauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_register)

        val progresR=findViewById<ProgressBar>(R.id.progressBaru)
        val sinip=findViewById<ImageView>(R.id.UsersSignin)

        val Usersbtnreg=findViewById<Button>(R.id.Usersregbutton)

        val Usersregname=findViewById<EditText>(R.id.UsersregName)
        val Usersregemail=findViewById<EditText>(R.id.Usersregemail)
        val Usersregpass=findViewById<EditText>(R.id.Usersregpass)



        sinip.setOnClickListener {
            val intent= Intent(this, UsersLogin::class.java)
            startActivity(intent)
        }


        // firebase connection
        UsersaREauth = FirebaseAuth.getInstance()

        IndividualDatabaseReference= FirebaseDatabase.getInstance().getReference("INDIVIDUALUSERSDETAILS")

        // maintain the session
        if( UsersaREauth .currentUser!=null)
        {
            val intent= Intent(this, UsersADDItem::class.java)
            startActivity(intent)

        }


// firebase registration process start

        Usersbtnreg.setOnClickListener {


            val UserrNameR=Usersregname.text.toString()
            val UserremailR=Usersregemail.text.toString()
            val UserrPasswordR=Usersregpass.text.toString()


            progresR.visibility= View.VISIBLE

            UsersaREauth  .createUserWithEmailAndPassword(UserremailR,UserrPasswordR).addOnCompleteListener {

                if(it.isSuccessful)
                {

                    val currentUser=  UsersaREauth.currentUser

                    val userid= currentUser?.uid

                    val uj :String =userid.toString()
                    val w= IndividualUsers(UserremailR,UserrNameR,UserrPasswordR)

                    if (userid != null) {

                        IndividualDatabaseReference .child(uj).setValue(w).addOnSuccessListener {

                            Toast.makeText(this, "Individual user created", Toast.LENGTH_SHORT).show()
                            val intent= Intent(this,  UsersADDItem::class.java)
                            startActivity(intent)



                        }.addOnFailureListener {
                            Toast.makeText(this, "sorry server issue", Toast.LENGTH_SHORT).show()
                        }
                    }



                }


            }




        }



    }
}