package com.example.paypal.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
//    "https://android-kotlin-fun-mars-server.appspot.com/photos"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())//italian,covered
    .build()

private val retrofit = Retrofit.Builder()
   // .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    //converter factory helps me connvert a json object ot VO/POJO/Marsphoto

    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {

    @GET("photos")          //photos = enndpoint
   suspend fun getPhotos():List<MarsPhoto>

}

//whta is a object class in kotlin
object MarsApi {
    //by inn kotlin == delegation patter
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)

    }

}