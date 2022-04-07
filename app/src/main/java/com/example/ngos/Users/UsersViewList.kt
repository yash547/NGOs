package com.example.ngos.Users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ngos.R
import com.example.ngos.Restaurent.MyAdapter
import com.example.ngos.Restaurent.ResViewListFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class UsersViewList : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayLists : ArrayList<INdividualViewListFirebase>
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_view_list)

        userRecyclerview = findViewById(R.id.Resrecyclerview12)
        userRecyclerview.layoutManager = LinearLayoutManager(this)

        userRecyclerview.setHasFixedSize(true)

        // connecting the firebase realtime database data to XML ngosprofile data.
        user= FirebaseAuth.getInstance().currentUser

        userArrayLists = arrayListOf<INdividualViewListFirebase>()


        getUserData()

    }


    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("INDIVIDUAL_ADDFOOD_Items")

        dbref.addValueEventListener(object : ValueEventListener {

            val  userID= user?.uid;
            val uj :String =userID.toString()

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val map:Map<String,Object> = userSnapshot.getValue() as Map<String, Object>
                        var stdteacherid : Object? =map.get("indcurrentUserToken")
                        if((uj.equals(stdteacherid)))
                        {

                            var user = userSnapshot.getValue(INdividualViewListFirebase::class.java)
                            userArrayLists.add(user!!)
                        }




                    }

                   userRecyclerview.adapter = MyAdapter(userArrayLists)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}