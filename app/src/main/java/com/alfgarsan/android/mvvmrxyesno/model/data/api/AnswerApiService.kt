package com.alfgarsan.android.mvvmrxyesno.model.data.api

import io.reactivex.Observable
import retrofit2.http.GET

interface AnswerApiService {

    @GET("/api")
    fun getAnswer(): Observable<AnswerApiDto>
}