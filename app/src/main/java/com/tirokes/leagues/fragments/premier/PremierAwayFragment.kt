package com.tirokes.leagues.fragments.premier

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.premier.PremierAwayAdapter
import com.tirokes.leagues.adapters.premier.PremierOverallAdapter
import com.tirokes.leagues.databinding.FragmentPremierAwayBinding
import com.tirokes.leagues.models.apl.table.APLTable
import com.tirokes.leagues.models.apl.table.Away
import com.tirokes.leagues.models.apl.table.Results
import com.tirokes.leagues.network.premier.APIAway
import com.tirokes.leagues.network.premier.APIPremierOverall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PremierAwayFragment : Fragment() {
    private lateinit var binding : FragmentPremierAwayBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : PremierAwayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremierAwayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIAway::class.java)
        retrofit.getAway().enqueue(object : Callback<APLTable> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<APLTable>, response: Response<APLTable>) {

                adapters = PremierAwayAdapter(requireActivity(), response.body()!!.results.away.tables[0].rows)
                binding.rvAway.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvAway.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarAway.visibility = View.GONE
                binding.tvLoadindAway.visibility = View.GONE
            }

            override fun onFailure(call: Call<APLTable>, t: Throwable) {

            }
        })
    }
}