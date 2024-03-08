package com.tirokes.leagues.fragments.championship

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tirokes.leagues.RetrofitClient
import com.tirokes.leagues.adapters.championship.ChampionshipAdapter
import com.tirokes.leagues.adapters.premier.PremierOverallAdapter
import com.tirokes.leagues.databinding.FragmentChampionshipBinding
import com.tirokes.leagues.models.apl.table.APLTable
import com.tirokes.leagues.network.premier.APIPremierOverall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChampionshipFragment : Fragment() {
    private lateinit var binding : FragmentChampionshipBinding
    private var coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var coroutine : Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampionshipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}