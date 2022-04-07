package com.example.ngos.Restaurent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ngos.NGOS.MyAdapter
import com.example.ngos.NGOS.ViewListFirebase
import com.example.ngos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class RestaurentViewList : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayLists : ArrayList<ResViewListFirebase>
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurent_view_list)


        userRecyclerview = findViewById(R.id.Resrecyclerview)
        userRecyclerview.layoutManager = LinearLayoutManager(this)

        userRecyclerview.setHasFixedSize(true)

        // connecting the firebase realtime database data to XML ngosprofile data.
        user= FirebaseAuth.getInstance().currentUser

        userArrayLists = arrayListOf<ResViewListFirebase>()

        getUserData()
    }



    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Restaurent_ADDFOOD_Items")

        dbref.addValueEventListener(object : ValueEventListener {

            val  userID= user?.uid;
            val uj :String =userID.toString()

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val map:Map<String,Object> = userSnapshot.getValue() as Map<String, Object>
                        var stdteacherid : Object? =map.get("resCurrentUserToken")
                        if((uj.equals(stdteacherid)))
                        {

                            var user = userSnapshot.getValue(ResViewListFirebase::class.java)
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