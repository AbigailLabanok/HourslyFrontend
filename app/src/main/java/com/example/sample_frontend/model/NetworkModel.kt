
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
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true } // important for ignoring extra fields
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}