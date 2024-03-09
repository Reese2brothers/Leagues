package com.tirokes.leagues.adapters.premier

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tirokes.leagues.R
import com.tirokes.leagues.fragments.premier.PremierOverallFragment
import com.tirokes.leagues.models.apl.table.RowXX

class PremierOverallAdapter(private val contextA : Context, private val listTable: List<RowXX>,
                            private val callback: PremierOverallFragment
) : RecyclerView.Adapter<PremierOverallAdapter.ViewHolder>() {

    class ViewHolder(itemView : View)  : RecyclerView.ViewHolder(itemView) {

        var position: TextView? = null
        var image : ImageView? = null
        var name: TextView? = null
        var win: TextView? = null
        var draw: TextView? = null
        var loss: TextView? = null
        var goals: TextView? = null
        var missed: TextView? = null
        var points: TextView? = null
        var change: TextView? = null
        var promotion: TextView? = null
        var playersbutton: Button? = null

        init {
            position = itemView.findViewById(R.id.tvTeamPos)
            image = itemView.findViewById(R.id.ivTeamLabel)
            name = itemView.findViewById(R.id.tvTeamName)
            win = itemView.findViewById(R.id.tvTeamWin)
            draw = itemView.findViewById(R.id.tvTeamDraw)
            loss = itemView.findViewById(R.id.tvTeamLoss)
            goals = itemView.findViewById(R.id.tvTeamGoals)
            missed = itemView.findViewById(R.id.tvTeamMissed)
            points = itemView.findViewById(R.id.tvTeamPoints)
            change = itemView.findViewById(R.id.tvTeamChange)
            promotion = itemView.findViewById(R.id.tvTeamPromotion)
            playersbutton = itemView.findViewById(R.id.btPlayers)
        }
        interface onItemClick{
            fun onClickItem(index: Int, listTable: List<RowXX>)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_premier, parent, false))
    }
    override fun getItemCount(): Int {
        return listTable.size
    }
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teamId = listTable[position].team.id
        Log.d("TAG", "Team ID: $teamId")
        if (teamId != null) {
            val imageUrl = "https://1win-england-league.store/teamlogo/$teamId.png"
            Glide.with(contextA).load(imageUrl).into(holder.image!!)
        }
        holder.name?.text = listTable[position].team.name
        holder.position?.text = listTable[position].pos
        holder.win?.text = listTable[position].win
        holder.draw?.text = listTable[position].draw
        holder.loss?.text = listTable[position].loss
        holder.goals?.text = listTable[position].goalsfor
        holder.missed?.text = listTable[position].goalsagainst
        holder.change?.text = "Change : ${listTable[position].change}"
        holder.points?.text = listTable[position].points

        holder.playersbutton?.setOnClickListener {
            callback.onClickItem(position, listTable)
        }


    }

}