package com.example.cryptoanalysis.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoanalysis.data.model.FavouriteCoin
import com.example.cryptoanalysis.data.repo.FavouriteCoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouriteCoinViewModel(private val repo: FavouriteCoinRepository): ViewModel() {
    var favouriteCoinList = MutableLiveData<List<FavouriteCoin>>()
    var favouriteCoin = MutableLiveData<FavouriteCoin>()
    var job: Job? = null

    fun getAllFavouriteCoins() {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllFavouriteCoins()
            if(res.isSuccessful) {
                favouriteCoinList.postValue(res.body())
            } else {
                println("Not successful")
            }
        }
    }

    fun getFavouriteCoin(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getFavouriteCoin(id)
            if(res.isSuccessful) {
                favouriteCoin.postValue(res.body())
            } else {
                println("Not successful")
            }
        }
    }

    fun saveFavouriteCoin(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.saveFavouriteCoin(id)
//            if(res.isSuccessful) {
//                favouriteCoinList.postValue(res.body())
//            } else {
//                println("Not successful")
//            }
        }
    }

    fun deleteFavouriteCoin(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.deleteFavouriteCoin(id)
//            if(res.isSuccessful) {
//                favouriteCoinList.postValue(res.body())
//            } else {
//                println("Not successful")
//            }
        }
    }
}