package com.tirokes.leagues.fragments.premier

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.premier.PremierAwayAdapter
import com.tirokes.leagues.adapters.premier.PremierHomeAdapter
import com.tirokes.leagues.databinding.FragmentPremierHomeBinding
import com.tirokes.leagues.models.apl.table.APLTable
import com.tirokes.leagues.network.premier.APIAway
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PremierHomeFragment : Fragment() {
    private lateinit var binding : FragmentPremierHomeBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : PremierHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremierHomeBinding.inflate(inflater, container, false)
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

                adapters = PremierHomeAdapter(requireActivity(), response.body()!!.results.home.tables[0].rows)
                binding.rvHome.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvHome.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarHome.visibility = View.GONE
                binding.tvLoadindHome.visibility = View.GONE
            }

            override fun onFailure(call: Call<APLTable>, t: Throwable) {

            }
        })
    }
}