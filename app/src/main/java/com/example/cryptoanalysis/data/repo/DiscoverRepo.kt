package com.example.cryptoanalysis.data.repo

import com.example.cryptoanalysis.data.api.RetrofitInterface

class DiscoverRepo(var inter : RetrofitInterface)
{

    fun getAllCoins(limit:Int, offset: Int) = inter.getAllCoins(limit, offset)
}