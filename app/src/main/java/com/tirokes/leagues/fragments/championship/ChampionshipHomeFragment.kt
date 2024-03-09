package com.tirokes.leagues.fragments.championship

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.championship.ChampHomeAdapter
import com.tirokes.leagues.databinding.FragmentChampionshipHomeBinding
import com.tirokes.leagues.models.championship.table.CSTable
import com.tirokes.leagues.network.championship.APIChampHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChampionshipHomeFragment : Fragment() {
    private lateinit var binding : FragmentChampionshipHomeBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : ChampHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionshipHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIChampHome::class.java)
        retrofit.getChampHome().enqueue(object : Callback<CSTable> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<CSTable>, response: Response<CSTable>) {

                adapters = ChampHomeAdapter(requireActivity(), response.body()!!.results.home.tables[0].rows)
                binding.rvChampHome.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvChampHome.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarChampHome.visibility = View.GONE
                binding.tvLoadindChampHome.visibility = View.GONE
            }

            override fun onFailure(call: Call<CSTable>, t: Throwable) {

            }
        })
    }
}