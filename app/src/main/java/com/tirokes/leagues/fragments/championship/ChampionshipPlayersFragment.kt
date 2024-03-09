package com.tirokes.leagues.fragments.championship

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.R
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.championship.ChampPlayersAdapter
import com.tirokes.leagues.databinding.FragmentChampionshipPlayersBinding
import com.tirokes.leagues.models.players.Players
import com.tirokes.leagues.network.championship.APIChampPlayers
import com.tirokes.leagues.viewmodels.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChampionshipPlayersFragment : Fragment() {
    private lateinit var binding : FragmentChampionshipPlayersBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : ChampPlayersAdapter
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionshipPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView44.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clChamp, ChampionshipFragment())
                ?.commit()
        }

        val champteamname = dataModel.champteamname.value.toString()
        val teamid = dataModel.champ_team_id.value
        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIChampPlayers::class.java)
        coroutineScope.launch{
            retrofit.getChampPlayers(teamid.toString()).enqueue(object : Callback<Players> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<Players>, response: Response<Players>) {
                    adapters = ChampPlayersAdapter(response.body()!!.results, champteamname)
                    binding.rvChampPlayers.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvChampPlayers.adapter = adapters
                    adapters.notifyDataSetChanged()
                    binding.progressBarChampPlayers.visibility = View.GONE
                    binding.tvLoadingChampPlayers.visibility = View.GONE
                }

                override fun onFailure(call: Call<Players>, t: Throwable) {

                }
            })
        }
    }
}