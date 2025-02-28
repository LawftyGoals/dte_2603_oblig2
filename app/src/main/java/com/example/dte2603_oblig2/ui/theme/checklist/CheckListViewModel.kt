package com.example.dte2603_oblig2.ui.theme.checklist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dte2603_oblig2.data.CheckList
import com.example.dte2603_oblig2.data.UiState
import com.example.dte2603_oblig2.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CheckListViewModel : ViewModel() {

    private val repository = Repository()
    /*
    private val _checkListState = MutableStateFlow(CheckListSamples.CheckLists)
    val checkListState: StateFlow<MutableList<CheckList>> = _checkListState.asStateFlow()
    */

    val checkListState = mutableStateListOf<CheckList?>(null)
    var checkListCount by mutableIntStateOf(0)

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        updateCheckLists()
    }

    fun toggleCheckListColumns() {
        _uiState.update { currentState -> currentState.copy(isMultiColumn = !currentState.isMultiColumn) }
    }

    fun updateCheckLists() {
        checkListState.clear()
        checkListState.addAll(
            repository.getAllCheckLists()
        )
        updateCheckListCount()
    }

    fun updateCheckList(prevCheckList: CheckList, updatedCheckList: CheckList) {
        checkListState.clear()
        checkListState.addAll(repository.editCheckList(prevCheckList, updatedCheckList))
        updateCheckListCount()
    }

    fun deleteCheckList(checkList: CheckList) {
        checkListState.clear()
        checkListState.addAll(repository.removeCheckList(checkList))
        updateCheckListCount()
    }

    fun addCheckList(checkList: CheckList) {
        checkListState.clear()
        checkListState.addAll(repository.addCheckList(checkList))
        updateCheckListCount()
    }

    private fun updateCheckListCount() {
        checkListCount = checkListState.count()

    }

}