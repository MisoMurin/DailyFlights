package com.murin.dailyflights.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.murin.dailyflights.FlightsApplication.Companion.KIWI_BASE_URL
import com.murin.dailyflights.data.FlightsRepository
import com.murin.dailyflights.data.network.FlightsApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Moshi.Builder().build()
    }

    single {
        CoroutineCallAdapterFactory()
    }

    single {
        Retrofit.Builder()
            .baseUrl(getProperty<String>(KIWI_BASE_URL))
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()).withNullSerialization())
            .build()
    }

    single<FlightsApi> {
        get<Retrofit>().create(FlightsApi::class.java)
    }

    single {
        FlightsRepository.getInstance(get<FlightsApi>())
    }
}
