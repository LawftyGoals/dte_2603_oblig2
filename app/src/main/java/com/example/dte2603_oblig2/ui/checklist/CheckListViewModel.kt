package com.example.dte2603_oblig2.ui.checklist

import androidx.lifecycle.ViewModel
import com.example.dte2603_oblig2.data.CheckList
import com.example.dte2603_oblig2.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CheckListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun toggleCheckListColumns() {
        _uiState.update { currentState -> currentState.copy(isMultiColumn = !currentState.isMultiColumn) }
    }

    fun updateCheckList(prevCheckList: CheckList, updatedCheckList: CheckList) {
        _uiState.update { currentState ->
            val tempCheckLists = currentState.checkLists
            val targetCheckList = tempCheckLists.firstOrNull {
                it.name ==
                        prevCheckList
                            .name
            }
            if (targetCheckList != null) {
                targetCheckList.name = updatedCheckList.name
                targetCheckList.icon = targetCheckList.icon
                targetCheckList.checkListItems = targetCheckList.checkListItems
            }
            currentState.copy(
                checkLists = tempCheckLists,
                checkListCount = tempCheckLists.count()
            )
        }
    }

    fun deleteCheckList(checkList: CheckList) {
        _uiState.update { currentState ->
            val tempCheckLists = currentState.checkLists.filter { it != checkList }.toMutableList()
            currentState.copy(
                checkLists = tempCheckLists,
                checkListCount = tempCheckLists.count()

            )
        }
    }

    fun addCheckList(checkList: CheckList) {
        _uiState.update { currentState ->
            val tempCheckLists = currentState.checkLists
            tempCheckLists.add(checkList)
            currentState.copy(
                checkLists = tempCheckLists,
                checkListCount = tempCheckLists.count()

            )
        }
    }




}