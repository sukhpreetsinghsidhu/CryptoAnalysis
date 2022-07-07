package com.example.cryptoanalysis.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cryptoanalysis.data.api.Api
import com.example.cryptoanalysis.data.model.ResponseCoins
import com.example.cryptoanalysis.data.repo.DiscoverRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor (var repo : DiscoverRepo) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text

    fun getAllCoins(limit:Int, offset:Int): Observable<ResponseCoins> {
        Api.seturl("https://coinranking1.p.rapidapi.com/")
        Log.d("Retrofit: vm ", "${Api.geturl()}")
        return repo.getAllCoins(limit, offset )
    }
}