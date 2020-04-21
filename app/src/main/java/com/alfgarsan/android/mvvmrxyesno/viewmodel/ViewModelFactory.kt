package com.alfgarsan.android.mvvmrxyesno.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alfgarsan.android.mvvmrxyesno.model.data.repository.FactoryAnswerRepository

/**
 * Factory for ViewModels
 */
class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(AnswerViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return AnswerViewModel(
                    FactoryAnswerRepository.getAnswerRepo()
                ) as T
            }

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}