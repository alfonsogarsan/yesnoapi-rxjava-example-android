package com.alfgarsan.android.mvvmrxyesno.model.repository

import com.alfgarsan.android.mvvmrxyesno.model.data.api.AnswerApiDto
import com.alfgarsan.android.mvvmrxyesno.model.domain.Answer

fun AnswerApiDto.toDomain() = Answer(
    answer = answer,
    forced = forced,
    imageUrl = image
)