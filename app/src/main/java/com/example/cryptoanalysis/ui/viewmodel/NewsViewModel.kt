package com.example.cryptoanalysis.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoanalysis.data.api.Api
import com.example.cryptoanalysis.data.model.ResponseCoins
import com.example.cryptoanalysis.data.model.ResponseNews
import com.example.cryptoanalysis.data.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(var repo: NewsRepo) : ViewModel() {

    fun getAllNews(): Observable<ResponseNews> {
        Api.seturl("https://api.goperigon.com")
        //Log.d("Retrofit: vm ", "${Api.geturl()}")
        return repo.getAllNews()
    }
}