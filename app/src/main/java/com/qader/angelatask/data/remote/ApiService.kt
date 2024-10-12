package com.qader.angelatask.data.remote

import com.qader.angelatask.data.model.ProblemsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("3d82c761-9775-4caf-a5df-ad5a68739516")
    suspend fun getMedicines(): ProblemsResponse
}
