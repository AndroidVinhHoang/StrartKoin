package com.example.startkoin.api

import com.example.startkoin.repository.Repo
import com.example.startkoin.repository.RepoIpm
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

    val viewModelModule = module {
        viewModel {
            GenresViewModel(get())
        }
    }
    val repoModule = module {
        single {
            RepoIpm(get())
        }
    }
    val apiModule = module {
        fun provideApi(retrofit: Retrofit): API {
            return retrofit.create(API::class.java)
        }
        single { provideApi(get()) }
    }

    val retrofitModule = module {
        fun provideGson(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        }

        fun provideHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            return client
        }

        fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/genre/movie/")
                .addConverterFactory(GsonConverterFactory.create(factory))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }
        single { provideGson() }
        single { provideHttpClient() }
        single { provideRetrofit(get(), get()) }
    }


    /*
    companion object {
        private const val URL = ""
        private val apiInterface: API? = null
        fun getMovieService(): API {
            if (apiInterface != null) {
                return apiInterface
            }
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(API::class.java)
        }
    }

     */