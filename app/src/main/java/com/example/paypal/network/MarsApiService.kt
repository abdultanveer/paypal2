package com.example.paypal.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
//    "https://android-kotlin-fun-mars-server.appspot.com/photos"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {

    @GET("photos")          //photos = enndpoint
   suspend fun getPhotos():String

}

//whta is a object class in kotlin
object MarsApi {
    //by inn kotlin == delegation patter
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)

    }

}