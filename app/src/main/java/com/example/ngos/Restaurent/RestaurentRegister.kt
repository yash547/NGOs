package com.example.ngos.Restaurent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ngos.NGOS.NgosAddItem
import com.example.ngos.NGOS.NgosLogin
import com.example.ngos.NGOS.Users
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RestaurentRegister : AppCompatActivity() {


    private  lateinit var ResDatabaseReference : DatabaseReference
    private lateinit var RestaurentREauth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurent_register)

        val sinipR=findViewById<ImageView>(R.id.RestaurentSignin)

        val btnregR=findViewById<Button>(R.id.Restaurentregbutton)

        val progresR=findViewById<ProgressBar>(R.id.progressBar)

        val restaurentregname=findViewById<EditText>(R.id.RestaurentregName)
        val restaurentregemail=findViewById<EditText>(R.id.Restaurentregemail)
        val restaurentregpass=findViewById<EditText>(R.id.Restaurentregpass)


        sinipR.setOnClickListener {
            val intent= Intent(this, RestaurentLogin::class.java)
            startActivity(intent)
        }



        // firebase connection
        RestaurentREauth  = FirebaseAuth.getInstance()

        ResDatabaseReference= FirebaseDatabase.getInstance().getReference("RESTAURENTUSERSDETAILS")

        // maintain the session
        if( RestaurentREauth .currentUser!=null)
        {
            val intent= Intent(this,RestaurentADDItem::class.java)
            startActivity(intent)
        }


// firebase registration process start

        btnregR.setOnClickListener {

            val resNameR=restaurentregname.text.toString()
            val resemailR=restaurentregemail.text.toString()
            val resPasswordR=restaurentregpass.text.toString()

            progresR.visibility= View.VISIBLE

            RestaurentREauth  .createUserWithEmailAndPassword(resemailR,resPasswordR).addOnCompleteListener {

                if(it.isSuccessful)
                {

                    val currentUser= RestaurentREauth.currentUser

                    val userid= currentUser?.uid

                    val uj :String =userid.toString()
                    val w= ResUsers(resemailR,resNameR,resPasswordR)

                    if (userid != null) {

                        ResDatabaseReference .child(uj).setValue(w).addOnSuccessListener {

                            Toast.makeText(this, "Restaurent user created", Toast.LENGTH_SHORT).show()
                            val intent= Intent(this, RestaurentADDItem::class.java)
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