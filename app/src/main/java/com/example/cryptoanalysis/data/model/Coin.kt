package com.example.cryptoanalysis.data.model

data class Coin(val symbol: String = "",
                val marketCap: String = "",
                val color: String = "#000000",
                val change: String = "",
                val btcPrice: String = "",
                val listedAt: Int = 0,
                val uuid: String = "",
                val HVolume: String = "",
                val tier: Int = 0,
                val coinrankingUrl: String = "",
                val price: Float = 0.0f,
                val name: String = "",
                val lowVolume: Boolean = false,
                val rank: Int = 0,
                val iconUrl: String = ""){}