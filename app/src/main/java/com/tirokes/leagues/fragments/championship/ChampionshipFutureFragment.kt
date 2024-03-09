package com.tirokes.leagues.fragments.championship

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.R
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.championship.ChampFutureAdapter
import com.tirokes.leagues.databinding.FragmentChampionshipFutureBinding
import com.tirokes.leagues.models.championship.games.FutureGames
import com.tirokes.leagues.network.championship.APIChampFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChampionshipFutureFragment : Fragment() {
    private lateinit var binding : FragmentChampionshipFutureBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : ChampFutureAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionshipFutureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBackChampFuture.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clChamp, ChampionshipFragment())
                ?.commit()
        }

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIChampFuture::class.java)
        coroutineScope.launch {
            retrofit.getChampFuture().enqueue(object : Callback<FutureGames> {
                @SuppressLint("NotifyDataSetChanged")
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onResponse(call: Call<FutureGames>, response: Response<FutureGames>) {
                    adapters = ChampFutureAdapter(requireActivity(), response.body()!!.games_pre)
                    Log.d("TAG", response.body().toString())
                    binding.rvChampFuture.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvChampFuture.adapter = adapters
                    adapters.notifyDataSetChanged()
                    binding.progressBarChampFuture.visibility = View.GONE
                    binding.tvLoadingChampFuture.visibility = View.GONE
                    Log.d("TAG", response.body().toString())
                }
                override fun onFailure(call: Call<FutureGames>, t: Throwable) {

                }
            })
        }

    }
}