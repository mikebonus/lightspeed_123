package com.lightspeed.lightspeedproject.di

import android.app.Application
import androidx.room.Room
import com.lightspeed.lightspeedproject.api.LightspeedApi
import com.lightspeed.lightspeedproject.data.LightspeedDatabase
import com.lightspeed.lightspeedproject.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): LightspeedApi =
        retrofit.create(LightspeedApi::class.java)

    // MODULE #3: d/b instance
    @Provides
    @Singleton
    fun provideDatabase(app: Application): LightspeedDatabase =
        Room.databaseBuilder(app, LightspeedDatabase::class.java, "lightspeed database")
            .build()

}