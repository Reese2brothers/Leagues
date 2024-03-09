package com.tirokes.leagues.fragments.one

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
import com.tirokes.leagues.adapters.one.OneViewPagerAdapter
import com.tirokes.leagues.adapters.premier.PremierAdapter
import com.tirokes.leagues.adapters.premier.PremierViewPagerAdapter
import com.tirokes.leagues.databinding.FragmentOneBinding
import com.tirokes.leagues.fragments.premier.PremierFutureFragment
import com.tirokes.leagues.models.apl.table.APLTable
import com.tirokes.leagues.models.leagueone.table.One
import com.tirokes.leagues.network.one.APIOne
import com.tirokes.leagues.network.premier.APIPremier
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


class OneFragment : Fragment() {
    private lateinit var binding : FragmentOneBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIOne::class.java)
        coroutine = coroutineScope.launch {
            retrofit.getOneName().enqueue(object : Callback<One> {
                override fun onResponse(call: Call<One>, response: Response<One>) {
                    binding.tvNameLeagueOne.text = response.body()!!.results.season.name
                    binding.tvStartOne.text = timeS(response.body()!!.results.season.start_time.toInt())
                    binding.tvEndOne.text = timeS(response.body()!!.results.season.end_time.toInt())
                    coroutine?.cancel()
                }
                override fun onFailure(call: Call<One>, t: Throwable) {

                }
            })
        }
        initial()
        binding.btFutureMatchesOne.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.clOne, OneFutureFragment())
                ?.commit()
        }

    }
    private fun initial() {
        binding.vpPager21.adapter = OneViewPagerAdapter(requireActivity())
        binding.tabLayout22.tabIconTint = null
        TabLayoutMediator(binding.tabLayout22, binding.vpPager21) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Overall"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager21.setPageTransformer(DepthPageTransformer())
                }

                1 -> {
                    tab.text = "Away"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager21.setPageTransformer(DepthPageTransformer())
                }

                2 -> {
                    tab.text = "Home"
                    tab.icon?.setTint(ContextCompat.getColor(requireActivity(), R.color.lightblue))
                    binding.vpPager21.setPageTransformer(DepthPageTransformer())
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