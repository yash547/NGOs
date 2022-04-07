package com.example.ngos.Restaurent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ngos.R


class MyAdapter(private val userList : ArrayList<ResViewListFirebase>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.restaurentviewlistr,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.FooditemsList.text = currentitem.resfooditems
        holder.FeedcountList.text = currentitem.resfeedcount
        holder.DatetimeList.text = currentitem.resdate
        holder.StatusList.text = currentitem.resstatus

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val FooditemsList: TextView = itemView.findViewById(R.id.reSfooditemslist)
        val FeedcountList: TextView = itemView.findViewById(R.id.reSfeedcountlist)
        val DatetimeList: TextView = itemView.findViewById(R.id.reSdatetimelist)

        val StatusList: TextView =itemView.findViewById(R.id.reSstatuslist)


    }
}


