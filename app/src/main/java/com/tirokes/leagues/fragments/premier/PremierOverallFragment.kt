package com.tirokes.leagues.fragments.premier

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.R
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.premier.PremierOverallAdapter
import com.tirokes.leagues.databinding.FragmentPremierOverallBinding
import com.tirokes.leagues.models.apl.table.APLTable
import com.tirokes.leagues.models.apl.table.RowXX
import com.tirokes.leagues.network.premier.APIPremierOverall
import com.tirokes.leagues.viewmodels.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PremierOverallFragment : Fragment(), PremierOverallAdapter.ViewHolder.onItemClick {
    private lateinit var binding : FragmentPremierOverallBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : PremierOverallAdapter
    lateinit var listTable: List<RowXX>
    private val dataModel : DataModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPremierOverallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIPremierOverall::class.java)
        //coroutineScope.launch {
            retrofit.getOverall().enqueue(object : Callback<APLTable> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<APLTable>, response: Response<APLTable>) {
                    listTable = ArrayList<RowXX>()
                    adapters = PremierOverallAdapter(requireActivity(), response.body()!!.results.overall.tables[0].rows, this@PremierOverallFragment)
                    binding.rvOverall.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvOverall.adapter = adapters
                    adapters.notifyDataSetChanged()
                    binding.progressBarOverall.visibility = View.GONE
                    binding.tvLoadindOverall.visibility = View.GONE
                }

                override fun onFailure(call: Call<APLTable>, t: Throwable) {

                }
            })
       // }

    }
    override fun onClickItem(index: Int, listTable: List<com.tirokes.leagues.models.apl.table.RowXX>
    ) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.clPremier, PremierPlayersFragment())
            ?.commit()

        val team_id = listTable[index].team.id
        dataModel.premier_team_id.value = team_id
    }
}