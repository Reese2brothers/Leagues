package com.tirokes.leagues.adapters.one

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tirokes.leagues.R
import com.tirokes.leagues.models.leagueone.games.GamesPre
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class OneFutureAdapter(private val contextA: Context,
                       private val listTable: List<GamesPre>
) : RecyclerView.Adapter<OneFutureAdapter.ViewHolder>() {
    class ViewHolder(itemView : View, contextV : Context) : RecyclerView.ViewHolder(itemView) {
        val context = contextV
        var away: TextView? = null
        var home: TextView? = null
        var league: TextView? = null
        var time: TextView? = null



        init {
            away = itemView.findViewById(R.id.tvItemAway)
            home = itemView.findViewById(R.id.tvItemHome)
            league = itemView.findViewById(R.id.tvItemLeague)
            time = itemView.findViewById(R.id.tvItemTime)
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
        return ViewHolder(LayoutInflater.from(contextA).inflate(R.layout.item_premier_future, parent, false), contextA)
    }
    override fun getItemCount(): Int {
        return listTable.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listMatch = listTable[position]
        holder.away?.text = listMatch.away.name
        holder.home?.text = listMatch.home.name
        holder.league?.text = listMatch.league.name
        holder.time?.text = holder.timeS(listMatch.time.toInt())
    }
}
