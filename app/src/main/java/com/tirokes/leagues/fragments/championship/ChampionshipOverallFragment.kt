package com.tirokes.leagues.fragments.championship

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
import com.tirokes.leagues.adapters.championship.ChampOverallAdapter
import com.tirokes.leagues.databinding.FragmentChampionshipOverallBinding
import com.tirokes.leagues.models.championship.table.CSTable
import com.tirokes.leagues.models.championship.table.RowXX
import com.tirokes.leagues.network.championship.APIChampOverall
import com.tirokes.leagues.viewmodels.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChampionshipOverallFragment : Fragment(), ChampOverallAdapter.ViewHolder.onItemClick {
    private lateinit var binding : FragmentChampionshipOverallBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : ChampOverallAdapter
    lateinit var listTable: List<RowXX>
    private val dataModel : DataModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionshipOverallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIChampOverall::class.java)
        //coroutineScope.launch {
        retrofit.getChampOverall().enqueue(object : Callback<CSTable> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<CSTable>, response: Response<CSTable>) {
                listTable = ArrayList<RowXX>()
                adapters = ChampOverallAdapter(requireActivity(), response.body()!!.results.overall.tables[0].rows, this@ChampionshipOverallFragment)
                binding.rvChampOverall.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvChampOverall.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarChampOverall.visibility = View.GONE
                binding.tvLoadindChampOverall.visibility = View.GONE
            }

            override fun onFailure(call: Call<CSTable>, t: Throwable) {

            }
        })
        // }

    }
    override fun onClickItem(index: Int, listTable: List<RowXX>) {

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.clChamp, ChampionshipPlayersFragment())
            ?.commit()

        val champteamname = listTable[index].team.name
        val team_id = listTable[index].team.id
        dataModel.champ_team_id.value = team_id
        dataModel.champteamname.value = champteamname
    }

}