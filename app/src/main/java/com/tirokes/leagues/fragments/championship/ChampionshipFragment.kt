package com.tirokes.leagues.fragments.championship

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.tirokes.leagues.DepthPageTransformer
import com.tirokes.leagues.R
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.championship.ChampViewPagerAdapter
import com.tirokes.leagues.adapters.premier.PremierViewPagerAdapter
import com.tirokes.leagues.databinding.FragmentChampionshipBinding
import com.tirokes.leagues.models.championship.table.CSTable
import com.tirokes.leagues.network.championship.APIChampionship
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class ChampionshipFragment : Fragment() {
    private lateinit var binding : FragmentChampionshipBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionshipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIChampionship::class.java)
        coroutine = coroutineScope.launch {
            retrofit.getChampName().enqueue(object : Callback<CSTable> {
                override fun onResponse(call: Call<CSTable>, response: Response<CSTable>) {
                    binding.tvNameLeagueChamp.text = response.body()!!.results.season.name
                    binding.tvStartChamp.text = timeS(response.body()!!.results.season.start_time.toInt())
                    binding.tvEndChamp.text = timeS(response.body()!!.results.season.end_time.toInt())
                    coroutine?.cancel()
                }
                override fun onFailure(call: Call<CSTable>, t: Throwable) {

                }
            })
        }
        initial()
        binding.btFutureMatchesChamp.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clChamp, ChampionshipFutureFragment())
                ?.commit()
        }

    }
    private fun initial() {
        binding.vpPager20.adapter = ChampViewPagerAdapter(requireActivity())
        binding.tabLayout2.tabIconTint = null
        TabLayoutMediator(binding.tabLayout2, binding.vpPager20) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Overall"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager20.setPageTransformer(DepthPageTransformer())
                }

                1 -> {
                    tab.text = "Away"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager20.setPageTransformer(DepthPageTransformer())
                }

                2 -> {
                    tab.text = "Home"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager20.setPageTransformer(DepthPageTransformer())
                }
            }

        }.attach()
    }
    @SuppressLint("SimpleDateFormat")
    fun timeS(unixSeconds: Int): String {
        val date = Date(unixSeconds * 1000L)
        val sdf = SimpleDateFormat("yyyy-MM-dd ")
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }
}