package com.tirokes.leagues.fragments.one

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
import com.tirokes.leagues.adapters.one.OneFutureAdapter
import com.tirokes.leagues.databinding.FragmentOneFutureBinding
import com.tirokes.leagues.models.leagueone.games.FutureGames
import com.tirokes.leagues.network.one.APIOneFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneFutureFragment : Fragment() {
    private lateinit var binding : FragmentOneFutureBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : OneFutureAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneFutureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBackOneFuture.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clOne, OneFragment())
                ?.commit()
        }

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIOneFuture::class.java)
        coroutineScope.launch {
            retrofit.getOneFuture().enqueue(object : Callback<FutureGames> {
                @SuppressLint("NotifyDataSetChanged")
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onResponse(call: Call<FutureGames>, response: Response<FutureGames>) {
                    adapters = OneFutureAdapter(requireActivity(), response.body()!!.games_pre)
                    Log.d("TAG", response.body().toString())
                    binding.rvOneFuture.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvOneFuture.adapter = adapters
                    adapters.notifyDataSetChanged()
                    binding.progressBarOneFuture.visibility = View.GONE
                    binding.tvLoadingOneFuture.visibility = View.GONE
                    Log.d("TAG", response.body().toString())
                }
                override fun onFailure(call: Call<FutureGames>, t: Throwable) {

                }
            })
        }

    }
}