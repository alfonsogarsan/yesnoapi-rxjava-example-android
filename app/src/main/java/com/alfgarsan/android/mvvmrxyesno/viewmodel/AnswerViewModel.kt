package com.alfgarsan.android.mvvmrxyesno.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfgarsan.android.mvvmrxyesno.model.domain.Answer
import com.alfgarsan.android.mvvmrxyesno.model.domain.Result
import com.alfgarsan.android.mvvmrxyesno.model.data.repository.AnswerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AnswerViewModel (
    private val answerRepo: AnswerRepository
): ViewModel() {

    private var disposable: Disposable? = null

    val answer by lazy {
        MutableLiveData<Result<Answer>>()
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    fun loadAnswer() {
        disposable =
            answerRepo.getAnswer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data -> answer.setValue(Result.Success(data))},
                    { error -> answer.setValue(Result.Failure(error))},
                    { println("Getting answer completed")}
                )
    }
}
