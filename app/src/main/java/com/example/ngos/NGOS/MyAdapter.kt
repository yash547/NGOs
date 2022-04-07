package com.example.ngos.NGOS

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ngos.R

class MyAdapter(private val userList : ArrayList<ViewListFirebase>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.ngoviewlistn,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.FooditemsList.text = currentitem.fooditems
        holder.FeedcountList.text = currentitem.feedcount
        holder.DatetimeList.text = currentitem.date
        holder.StatusList.text = currentitem.status

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val FooditemsList: TextView = itemView.findViewById(R.id.fooditemslist)
        val FeedcountList: TextView = itemView.findViewById(R.id.feedcountlist)
        val DatetimeList:TextView = itemView.findViewById(R.id.datetimelist)

        val StatusList:TextView=itemView.findViewById(R.id.statuslist)


    }
}

