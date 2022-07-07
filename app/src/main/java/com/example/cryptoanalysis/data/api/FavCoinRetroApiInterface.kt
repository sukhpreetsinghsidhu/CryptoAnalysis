package com.example.cryptoanalysis.data.api

import com.example.cryptoanalysis.data.model.FavouriteCoin
import com.example.cryptoanalysis.utils.AccessToken
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface FavCoinRetroApiInterface {
    @GET("favourite/coin")
    suspend fun getFavouriteCoins(): Response<List<FavouriteCoin>>

    @GET("favourite/coin/:id")
    suspend fun getFavouriteCoin(id: String): Response<FavouriteCoin>

    @POST("favourite/coin/:id")
    suspend fun saveFavouriteCoin(id: String)

    @DELETE("favourite/coin/:id")
    suspend fun deleteFavouriteCoin(id: String)

    companion object {
        private const val BASE_URL = "https://us-central1-crypto-pirates.cloudfunctions.net/api/"
        fun create(): FavCoinRetroApiInterface {
            val client = okhttp3.OkHttpClient.Builder()
                .addInterceptor(OAuthInterceptor("Bearer", AccessToken.accessToken))
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(FavCoinRetroApiInterface::class.java)
        }
    }
}

class OAuthInterceptor(private val tokenType: String, private val accessToken: String?) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$tokenType $accessToken").build()
        return chain.proceed(request)
    }
}

//class DiscoverRepo(var inter : RetrofitInterface) {
//    fun getAllCoins() = inter.getAllCoins()
//}