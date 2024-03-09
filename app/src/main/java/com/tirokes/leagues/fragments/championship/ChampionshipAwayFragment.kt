package com.tirokes.leagues.fragments.championship

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.championship.ChampAwayAdapter
import com.tirokes.leagues.databinding.FragmentChampionshipAwayBinding
import com.tirokes.leagues.models.championship.table.CSTable
import com.tirokes.leagues.network.championship.APIChampAway
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChampionshipAwayFragment : Fragment() {
    private lateinit var binding : FragmentChampionshipAwayBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : ChampAwayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionshipAwayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIChampAway::class.java)
        retrofit.getChampAway().enqueue(object : Callback<CSTable> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<CSTable>, response: Response<CSTable>) {

                adapters = ChampAwayAdapter(requireActivity(), response.body()!!.results.away.tables[0].rows)
                binding.rvChampAway.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvChampAway.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarChampAway.visibility = View.GONE
                binding.tvLoadindChampAway.visibility = View.GONE
            }

            override fun onFailure(call: Call<CSTable>, t: Throwable) {

            }
        })
    }
}