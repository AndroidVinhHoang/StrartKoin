package com.example.startkoin.api

import com.example.startkoin.ui.GenresViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
 class ApiClient{
     companion object {
         fun getService(): API{
             val interceptor = HttpLoggingInterceptor()
             interceptor.level = HttpLoggingInterceptor.Level.BODY
             val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

             val retrofit = Retrofit.Builder()
                 .baseUrl("https://api.themoviedb.org/3/")
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(client)
                 .build()
             return retrofit.create(API::class.java)
         }
     }
 }