package com.tirokes.leagues.fragments.premier

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.tirokes.leagues.DepthPageTransformer
import com.tirokes.leagues.R
import com.tirokes.leagues.network.premier.APIPremier
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.premier.PremierAdapter
import com.tirokes.leagues.adapters.premier.PremierViewPagerAdapter
import com.tirokes.leagues.databinding.FragmentPremierBinding
import com.tirokes.leagues.models.apl.table.APLTable
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


class PremierFragment : Fragment() {
    private lateinit var binding : FragmentPremierBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : PremierAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIPremier::class.java)
        coroutine = coroutineScope.launch {
            retrofit.getName().enqueue(object : Callback<APLTable> {
                override fun onResponse(call: Call<APLTable>, response: Response<APLTable>) {
                    //val response = response.body()!!.results
                    binding.tvNameLeague.text = response.body()!!.results.season.name
                    binding.tvStart.text = timeS(response.body()!!.results.season.start_time.toInt())
                    binding.tvEnd.text = timeS(response.body()!!.results.season.end_time.toInt())
                    coroutine?.cancel()
                }
                override fun onFailure(call: Call<APLTable>, t: Throwable) {

                }
            })
        }
        initial()
        binding.btFutureMatches.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clPremier, PremierFutureFragment())
                ?.commit()
        }

    }
    private fun initial() {
        binding.vpPager2.adapter = PremierViewPagerAdapter(requireActivity())
        binding.tabLayout.tabIconTint = null
        TabLayoutMediator(binding.tabLayout, binding.vpPager2) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Overall"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager2.setPageTransformer(DepthPageTransformer())
                }

                1 -> {
                    tab.text = "Away"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager2.setPageTransformer(DepthPageTransformer())
                }

                2 -> {
                    tab.text = "Home"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager2.setPageTransformer(DepthPageTransformer())
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