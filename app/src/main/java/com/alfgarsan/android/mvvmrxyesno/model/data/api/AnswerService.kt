package com.alfgarsan.android.mvvmrxyesno.model.data.api

import com.alfgarsan.android.mvvmrxyesno.model.domain.Answer
import io.reactivex.Observable
import retrofit2.http.GET

interface AnswerService {

    @GET("/api")
    fun getAnswer(): Observable<Answer>
}