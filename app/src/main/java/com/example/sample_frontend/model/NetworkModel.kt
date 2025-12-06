
package com.example.sample_frontend.model

//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object NetworkModule {
//    val api: CourseApi by lazy {
//        Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:5000") // Android emulator accessing local Flask server
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(CourseApi::class.java)
//    }
//}


import com.example.sample_frontend.model.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")   // 你的后端地址
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}