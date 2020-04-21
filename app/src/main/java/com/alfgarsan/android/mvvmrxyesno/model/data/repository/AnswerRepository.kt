package com.alfgarsan.android.mvvmrxyesno.model.data.repository

import com.alfgarsan.android.mvvmrxyesno.model.domain.Answer
import io.reactivex.Observable

interface AnswerRepository {

    fun getAnswer(): Observable<Answer>
}