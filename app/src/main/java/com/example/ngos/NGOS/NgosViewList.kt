package com.example.ngos.NGOS

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.time.LocalDateTime

class NgosViewList : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<ViewListFirebase>
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngos_view_list)


        userRecyclerview = findViewById(R.id.recyclerview)
        userRecyclerview.layoutManager = LinearLayoutManager(this)

        userRecyclerview.setHasFixedSize(true)

        // connecting the firebase realtime database data to XML ngosprofile data.
        user= FirebaseAuth.getInstance().currentUser

        userArrayList = arrayListOf<ViewListFirebase>()

        getUserData()



    }


    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("NGOS_ADDFOOD_Items")

        dbref.addValueEventListener(object : ValueEventListener {

            val  userID= user?.uid;
            val uj :String =userID.toString()

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val map:Map<String,Object> = userSnapshot.getValue() as Map<String, Object>
                         var stdteacherid : Object? =map.get("currentUserToken")
                         if((uj.equals(stdteacherid)))
                        {

                            var user = userSnapshot.getValue(ViewListFirebase::class.java)
                            userArrayList.add(user!!)
                        }




                    }

                    userRecyclerview.adapter = MyAdapter(userArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


}