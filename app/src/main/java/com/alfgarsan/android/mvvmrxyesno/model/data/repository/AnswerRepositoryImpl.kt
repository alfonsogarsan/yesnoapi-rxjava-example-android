package com.alfgarsan.android.mvvmrxyesno.model.data.repository

import com.alfgarsan.android.mvvmrxyesno.model.domain.Answer
import com.alfgarsan.android.mvvmrxyesno.model.data.api.AnswerService
import io.reactivex.Observable

class AnswerRepositoryImpl(
    private val answerService: AnswerService
    //In this example there isn't DAO for answer
) : AnswerRepository{

    override fun getAnswer(): Observable<Answer> {
        return answerService.getAnswer()
    }
}