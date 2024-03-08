package com.tirokes.leagues.adapters.premier

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tirokes.leagues.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

//class PremierFutureMatchesAdapter (private val contextA: Context,
//                    private val listMatches: List<MatchesItem>,
//                    private val callback: MatchActivity
//) : RecyclerView.Adapter<PremierFutureMatchesAdapter.ViewHolder>() {
//    class ViewHolder(itemView : View, contextV : Context) : RecyclerView.ViewHolder(itemView) {
//        val context = contextV
//        var away: TextView? = null
//        var home: TextView? = null
//        var league: TextView? = null
//        var time: TextView? = null
//        var awayOd: TextView? = null
//        var homeOd: TextView? = null
//        var drawOd: TextView? = null
//        var oddsOpen: Button? = null
//        var oddsClosed: Button? = null
//        var llOdds : LinearLayout? = null
//
//        init {
//            away = itemView.findViewById(R.id.tvItemAway)
//            home = itemView.findViewById(R.id.tvItemHome)
//            league = itemView.findViewById(R.id.tvItemLeague)
//            time = itemView.findViewById(R.id.tvItemTime)
//            awayOd = itemView.findViewById(R.id.tvAwayOd)
//            homeOd = itemView.findViewById(R.id.tvHomeOd)
//            drawOd = itemView.findViewById(R.id.tvDrawOd)
//            oddsOpen = itemView.findViewById(R.id.btItemOpenOdds)
//            oddsClosed = itemView.findViewById(R.id.btItemClosedOdds)
//            llOdds = itemView.findViewById(R.id.llOdds)
//        }
//        @SuppressLint("SimpleDateFormat")
//        fun timeS(unixSeconds: Int): String {
//            val date = Date(unixSeconds * 1000L)
//            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//            sdf.timeZone = TimeZone.getTimeZone("UTC")
//            sdf.timeZone = TimeZone.getDefault()
//            return sdf.format(date)
//        }
//        interface OnClickItem{
//            fun onClickOpen(position: Int)
//            fun onClickClosed(position: Int)
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(contextA).inflate(R.layout.item_matches_view, parent, false), contextA)
//    }
//    override fun getItemCount(): Int {
//        return listMatches.size
//    }
//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val listMatch = listMatches[position]
//        holder.away?.text = listMatch.away
//        holder.home?.text = listMatch.home
//        holder.league?.text = listMatch.league
//        holder.time?.text = holder.timeS(listMatch.time.toInt())
//
//    }
//}
