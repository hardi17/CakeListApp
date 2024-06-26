package com.hardi.cakelist.di.module

import android.content.Context
import com.hardi.cakelist.data.api.NetworkService
import com.hardi.cakelist.di.BaseUrl
import com.hardi.cakelist.utils.DefaultDispatcherProvider
import com.hardi.cakelist.utils.DispatcherProvider
import com.hardi.cakelist.utils.internetcheck.InternetConnected
import com.hardi.cakelist.utils.internetcheck.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String =
        "https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0\n" +
                "ec758ff5ae92b7b13fe4d57d34e1dc/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return InternetConnected(context)
    }

    @Provides
    @Singleton
    fun provideDispatcher() : DispatcherProvider = DefaultDispatcherProvider()

}