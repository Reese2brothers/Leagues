package com.tirokes.leagues.fragments.premier

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.R
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.premier.PremierAdapter
import com.tirokes.leagues.adapters.premier.PremierFutureAdapter
import com.tirokes.leagues.databinding.FragmentPremierBinding
import com.tirokes.leagues.databinding.FragmentPremierFutureBinding
import com.tirokes.leagues.models.apl.games.FutureGames
import com.tirokes.leagues.network.premier.APIPremierFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PremierFutureFragment : Fragment() {
    private lateinit var binding : FragmentPremierFutureBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : PremierFutureAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremierFutureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBackPremierFuture.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clPremier, PremierFragment())
                ?.commit()
        }

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIPremierFuture::class.java)
        coroutineScope.launch {
            retrofit.getPremierFuture().enqueue(object : Callback<FutureGames> {
                @SuppressLint("NotifyDataSetChanged")
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onResponse(call: Call<FutureGames>, response: Response<FutureGames>) {
                    adapters = PremierFutureAdapter(requireActivity(), response.body()!!.games_pre)
                    Log.d("TAG", response.body().toString())
                    binding.rvPremierFuture.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvPremierFuture.adapter = adapters
                     adapters.notifyDataSetChanged()
                        binding.progressBarPremierFuture.visibility = View.GONE
                        binding.tvLoadingPremierFuture.visibility = View.GONE
                        Log.d("TAG", response.body().toString())
                }
                override fun onFailure(call: Call<FutureGames>, t: Throwable) {

                }
            })
        }

    }
}