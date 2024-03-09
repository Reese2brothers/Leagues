package com.tirokes.leagues.fragments.one

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.R
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.one.OneOverallAdapter
import com.tirokes.leagues.databinding.FragmentOneOverallBinding
import com.tirokes.leagues.models.leagueone.table.One
import com.tirokes.leagues.models.leagueone.table.RowXX
import com.tirokes.leagues.network.one.APIOneOverall
import com.tirokes.leagues.viewmodels.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OneOverallFragment : Fragment(), OneOverallAdapter.ViewHolder.onItemClick {
    private lateinit var binding : FragmentOneOverallBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    lateinit var adapters : OneOverallAdapter
    lateinit var listTable: List<RowXX>
    private val dataModel : DataModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneOverallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient()
            .getClient("https://1win-england-league.store/")
            .create(APIOneOverall::class.java)
        //coroutineScope.launch {
        retrofit.getOneOverall().enqueue(object : Callback<One> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<One>, response: Response<One>) {
                listTable = ArrayList<RowXX>()
                adapters = OneOverallAdapter(requireActivity(), response.body()!!.results.overall.tables[0].rows, this@OneOverallFragment)
                binding.rvOne.layoutManager = LinearLayoutManager(requireActivity())
                binding.rvOne.adapter = adapters
                adapters.notifyDataSetChanged()
                binding.progressBarOne.visibility = View.GONE
                binding.tvLoadindOne.visibility = View.GONE
            }

            override fun onFailure(call: Call<One>, t: Throwable) {

            }
        })
        // }

    }
    override fun onClickItem(index: Int, listTable: List<RowXX>) {

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.clOne, OnePlayersFragment())
            ?.commit()

        val team_id = listTable[index].team.id
        dataModel.one_team_id.value = team_id
    }

}