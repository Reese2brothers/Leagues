package com.tirokes.leagues.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {

    val premier_team_id : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val champ_team_id : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val one_team_id : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val premierteamname : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val champteamname : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val oneteamname : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}