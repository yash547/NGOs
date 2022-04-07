package com.example.ngos.Users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ngos.R


class MyAdapter(private val userList : ArrayList<INdividualViewListFirebase>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.individualviewlisti,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.FooditemsList.text = currentitem.indfooditems
        holder.FeedcountList.text = currentitem.indfeedcount
        holder.DatetimeList.text = currentitem.inddate
        holder.StatusList.text = currentitem.indstatus

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val FooditemsList: TextView = itemView.findViewById(R.id.usersSfooditemslist)
        val FeedcountList: TextView = itemView.findViewById(R.id.usersSfeedcountlist)
        val DatetimeList: TextView = itemView.findViewById(R.id.usersSdatetimelist)

        val StatusList: TextView =itemView.findViewById(R.id.usersSstatuslist)


    }
}


