package com.example.stackoverflow.network

import com.example.stackoverflow.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClassInstance {
    private var retrofit: Retrofit? = null
    private var dataService: GetDataService? = null

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    val dataServiceInstance: GetDataService?
        get() {
            if (dataService == null) {
                if (retrofit == null)
                    retrofit = retrofitInstance
                dataService = retrofit!!.create(GetDataService::class.java)
            }
            return dataService
        }
}
