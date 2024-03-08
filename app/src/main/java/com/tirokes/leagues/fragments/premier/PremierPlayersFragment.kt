package com.tirokes.leagues.fragments.premier

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.R
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.premier.PremierAdapter
import com.tirokes.leagues.adapters.premier.PremierPlayersAdapter
import com.tirokes.leagues.databinding.FragmentPremierBinding
import com.tirokes.leagues.databinding.FragmentPremierPlayersBinding
import com.tirokes.leagues.models.players.Players
import com.tirokes.leagues.network.premier.APIPremierPlayers
import com.tirokes.leagues.viewmodels.PremierDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PremierPlayersFragment : Fragment() {
    private lateinit var binding : FragmentPremierPlayersBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : PremierPlayersAdapter
    private val dataModel : PremierDataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremierPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView4.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clPremier, PremierFragment())
                ?.commit()
        }

        val teamid = dataModel.team_id.value
        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIPremierPlayers::class.java)
        coroutineScope.launch{
            retrofit.getPlayers(teamid.toString()).enqueue(object : Callback<Players> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<Players>, response: Response<Players>) {
                        adapters = PremierPlayersAdapter(response.body()!!.results)
                        binding.rvPremierPlayers.layoutManager = LinearLayoutManager(requireActivity())
                        binding.rvPremierPlayers.adapter = adapters
                        adapters.notifyDataSetChanged()
                    binding.progressBarPlayers.visibility = View.GONE
                    binding.tvLoadingPlayers.visibility = View.GONE
                }

                override fun onFailure(call: Call<Players>, t: Throwable) {

                }
            })
        }
    }
}
