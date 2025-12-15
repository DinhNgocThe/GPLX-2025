package com.utc.driverxy.presentation.wrongQuestion

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.domain.usecase.question.GetWrongQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WrongQuestionViewModel(
    private val getWrongQuestion: GetWrongQuestion
) : BaseMviViewModel<WrongQuestionIntent, WrongQuestionState, WrongQuestionEvent>() {

    init {
        loadWrongQuestions()
    }

    private fun loadWrongQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            val questions = getWrongQuestion()
            updateState { copy(wrongQuestions = questions) }
        }
    }

    override fun initState(): WrongQuestionState {
        return WrongQuestionState()
    }

    override fun processIntent(intent: WrongQuestionIntent) {

    }
}