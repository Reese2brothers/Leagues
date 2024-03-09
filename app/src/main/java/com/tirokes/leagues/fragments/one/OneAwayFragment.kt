package com.tirokes.leagues.fragments.one

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.one.OneAwayAdapter
import com.tirokes.leagues.databinding.FragmentOneAwayBinding
import com.tirokes.leagues.models.leagueone.table.One
import com.tirokes.leagues.network.one.APIOneAway
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OneAwayFragment : Fragment() {
    private lateinit var binding : FragmentOneAwayBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : OneAwayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneAwayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIOneAway::class.java)
        retrofit.getOneAway().enqueue(object : Callback<One> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<One>, response: Response<One>) {

                adapters = OneAwayAdapter(requireActivity(), response.body()!!.results.away.tables[0].rows)
                binding.rvOneAway.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvOneAway.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarOneAway.visibility = View.GONE
                binding.tvLoadindOneAway.visibility = View.GONE
            }

            override fun onFailure(call: Call<One>, t: Throwable) {

            }
        })
    }
}