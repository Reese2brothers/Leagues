package com.tirokes.leagues.fragments.one

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.one.OneHomeAdapter
import com.tirokes.leagues.databinding.FragmentOneHomeBinding
import com.tirokes.leagues.models.leagueone.table.One
import com.tirokes.leagues.network.one.APIOneHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OneHomeFragment : Fragment() {
    private lateinit var binding : FragmentOneHomeBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : OneHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIOneHome::class.java)
        retrofit.getOneHome().enqueue(object : Callback<One> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<One>, response: Response<One>) {

                adapters = OneHomeAdapter(requireActivity(), response.body()!!.results.home.tables[0].rows)
                binding.rvOneHome.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvOneHome.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarOneHome.visibility = View.GONE
                binding.tvLoadindOneHome.visibility = View.GONE
            }

            override fun onFailure(call: Call<One>, t: Throwable) {

            }
        })
    }
}