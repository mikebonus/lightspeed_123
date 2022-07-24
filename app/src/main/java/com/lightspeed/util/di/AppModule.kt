package com.lightspeed.util.di

import android.app.Application
import androidx.room.Room
import com.lightspeed.data.api.FirstApi
import com.lightspeed.domain.data.LightspeedDatabase
import com.lightspeed.util.Constants
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
    fun provideRestaurantApi(retrofit: Retrofit): FirstApi =
        retrofit.create(FirstApi::class.java)

    // MODULE #3: d/b instance
    @Provides
    @Singleton
    fun provideDatabase(app: Application): LightspeedDatabase =
        Room.databaseBuilder(app, LightspeedDatabase::class.java, "lightspeed database")
            .build()

}