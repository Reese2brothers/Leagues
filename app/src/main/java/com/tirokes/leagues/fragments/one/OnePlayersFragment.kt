package com.tirokes.leagues.fragments.one

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
import com.tirokes.leagues.adapters.one.OnePlayersAdapter
import com.tirokes.leagues.databinding.FragmentOnePlayersBinding
import com.tirokes.leagues.models.players.Players
import com.tirokes.leagues.network.one.APIOnePlayers
import com.tirokes.leagues.viewmodels.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnePlayersFragment : Fragment() {
    private lateinit var binding : FragmentOnePlayersBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : OnePlayersAdapter
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnePlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView444.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clOne, OneFragment())
                ?.commit()
        }

        val oneteamname = dataModel.oneteamname.value.toString()
        val teamid = dataModel.one_team_id.value
        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIOnePlayers::class.java)
        coroutineScope.launch{
            retrofit.getOnePlayers(teamid.toString()).enqueue(object : Callback<Players> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<Players>, response: Response<Players>) {
                    adapters = OnePlayersAdapter(response.body()!!.results, oneteamname )
                    binding.rvOnePlayers.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvOnePlayers.adapter = adapters
                    adapters.notifyDataSetChanged()
                    binding.progressBarOnePlayers.visibility = View.GONE
                    binding.tvLoadingOnePlayers.visibility = View.GONE
                }

                override fun onFailure(call: Call<Players>, t: Throwable) {

                }
            })
        }
    }
}