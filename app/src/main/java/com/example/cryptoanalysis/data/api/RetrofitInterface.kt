package com.example.cryptoanalysis.data.api


import android.util.Log
import com.example.cryptoanalysis.data.model.ResponseCoins
import com.example.cryptoanalysis.data.model.ResponseNews
import com.example.cryptoanalysis.data.repo.NewsRepo
import com.example.cryptoanalysis.data.repo.DiscoverRepo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.inject.Singleton

object Api {
    private var BASE_URL = ""
    fun seturl(url: String)  {
        BASE_URL = url
    }
    fun geturl(): String {
        return BASE_URL
    }
}
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  //  var baseurl = "https://coinranking1.p.rapidapi.com/";

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit  {
        Log.d("Retrofit  1: ", "${Api.geturl()}")
      return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(Api.geturl())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RetrofitInterface = retrofit.create(RetrofitInterface::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: RetrofitInterface) = DiscoverRepo(apiService)


    @Singleton
    @Provides
    fun provideApiService2(retrofit: Retrofit): NewsInterface = retrofit.create(NewsInterface::class.java)

    @Singleton
    @Provides
    fun providesRepository2(apiService: NewsInterface) = NewsRepo(apiService)

}


interface RetrofitInterface {
//var baseurl = "https://coinranking1.p.rapidapi.com/";
    @GET("coins?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h&orderBy=marketCap&orderDirection=desc")
   //&limit={limit}&offset={offset}
    @Headers("X-Rapidapi-Key: 870ba89cf6msh2b32fb922a04c36p1ab840jsn47d713b4fe85")
    fun getAllCoins( @Query("limit") limit: Int, @Query("offset") offset : Int) : Observable<ResponseCoins>

}

interface NewsInterface {
    //https://api.goperigon.com/v1/all?source=cnn.com&sortBy=date&apiKey=ae2b2187-86cb-4da4-bf48-e413951b0f42&topic=Cryptocurrency&from=2022-01-01T00:00:00
    // var baseurl = "https://crypto-news15.p.rapidapi.com";
    @GET("/v1/all?source=cnn.com&sortBy=date&apiKey=ae2b2187-86cb-4da4-bf48-e413951b0f42&topic=Cryptocurrency")
  //  @Headers("X-Rapidapi-Key: 16b1461be2msh515880f0029f0c3p1d5a8ejsne5376e712ac3")
    fun getAllNews() : Observable<ResponseNews>
}