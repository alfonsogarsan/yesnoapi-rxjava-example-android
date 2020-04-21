package com.alfgarsan.android.mvvmrxyesno.model.data.repository

import com.alfgarsan.android.mvvmrxyesno.model.data.api.AnswerApiService
import com.alfgarsan.android.mvvmrxyesno.model.data.api.RetrofitClient

object FactoryAnswerRepository {

    private var answerRepoImpl : AnswerRepository? = null

    fun getAnswerRepo() : AnswerRepository {
        if (answerRepoImpl== null){
            answerRepoImpl = AnswerRepositoryImpl(
                answerApiService = RetrofitClient.getService(AnswerApiService::class.java)
            )
        }
        return  answerRepoImpl as AnswerRepository
    }
}