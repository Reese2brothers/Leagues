package com.tirokes.leagues.adapters.premier

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tirokes.leagues.R
import com.tirokes.leagues.models.players.Result
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class PremierPlayersAdapter(private val listResult: List<Result>, private val teamname : String
) : RecyclerView.Adapter<PremierPlayersAdapter.ViewHolder>() {

    class ViewHolder(itemView : View)  : RecyclerView.ViewHolder(itemView) {

        var birthday: TextView? = null
        var cc : TextView? = null
        var foot: TextView? = null
        var height: TextView? = null
        var marketvalue: TextView? = null
        var membersince: TextView? = null
        var name: TextView? = null
        var position: TextView? = null
        var shirtnumber: TextView? = null
        var weight: TextView? = null
        var teamnameleague : TextView? = null

        init {
            birthday = itemView.findViewById(R.id.tvBirthDate)
            cc = itemView.findViewById(R.id.tvCC)
            foot = itemView.findViewById(R.id.tvFoot)
            height = itemView.findViewById(R.id.tvHeight)
            marketvalue = itemView.findViewById(R.id.tvMarketValue)
            membersince = itemView.findViewById(R.id.tvMemberSince)
            name = itemView.findViewById(R.id.tvName)
            position = itemView.findViewById(R.id.tvPosition)
            shirtnumber = itemView.findViewById(R.id.tvShirtNumber)
            weight = itemView.findViewById(R.id.tvWeight)
            teamnameleague = itemView.findViewById(R.id.tvTeamNameLeague)
        }
        @SuppressLint("SimpleDateFormat")
        fun timeS(unixSeconds: Int): String {
            val date = Date(unixSeconds * 1000L)
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_players, parent, false))
    }

    override fun getItemCount(): Int {
        return listResult.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listItem = listResult[position]
        holder.membersince?.text = holder.timeS(listItem.membersince.toInt())
        holder.birthday?.text = listItem.birthdate
        holder.cc?.text = listItem.cc
        holder.foot?.text = listItem.foot
        holder.height?.text = listItem.height
        holder.marketvalue?.text = listItem.marketvalue
        holder.name?.text = listItem.name
        holder.position?.text = listItem.position
        holder.shirtnumber?.text = listItem.shirtnumber
        holder.weight?.text = listItem.weight
        holder.teamnameleague?.text = teamname
    }
}