package com.tirokes.leagues.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PremierDataModel : ViewModel() {

    val team_id : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}