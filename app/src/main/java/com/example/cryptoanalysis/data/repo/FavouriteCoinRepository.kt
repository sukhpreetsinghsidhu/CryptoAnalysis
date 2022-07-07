package com.example.cryptoanalysis.data.repo

import com.example.cryptoanalysis.data.api.FavCoinRetroApiInterface

class FavouriteCoinRepository(private val inter: FavCoinRetroApiInterface) {
    suspend fun getAllFavouriteCoins() = inter.getFavouriteCoins()
    suspend fun getFavouriteCoin(id: String) = inter.getFavouriteCoin(id)
    suspend fun saveFavouriteCoin(id: String) = inter.saveFavouriteCoin(id)
    suspend fun deleteFavouriteCoin(id: String) = inter.deleteFavouriteCoin(id)
}