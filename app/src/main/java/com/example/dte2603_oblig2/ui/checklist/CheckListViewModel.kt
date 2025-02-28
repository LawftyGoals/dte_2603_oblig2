package com.example.dte2603_oblig2.ui.checklist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dte2603_oblig2.data.CheckList
import com.example.dte2603_oblig2.data.CheckListItem
import com.example.dte2603_oblig2.data.DTOCheckList
import com.example.dte2603_oblig2.data.DTOCheckListItem
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
                it.checkListId ==
                        prevCheckList
                            .checkListId
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
            val tempCheckLists = currentState.checkLists
            tempCheckLists.remove(checkList)
            currentState.copy(
                checkLists = tempCheckLists,
                checkListCount = tempCheckLists.count()

            )
        }
    }

    fun addCheckList(checkList: DTOCheckList) {
        _uiState.update { currentState ->
            val tempCheckLists = currentState.checkLists
            tempCheckLists.add(
                CheckList(
                    currentState.checkListIdValue, checkList.name, checkList
                        .icon, checkList.checkListItems
                )
            )
            currentState.copy(
                checkLists = tempCheckLists,
                checkListCount = tempCheckLists.count(),
                checkListIdValue = currentState.checkListItemIdValue + 1

            )
        }
    }

    fun updateCheckListItemState(
        checkList: CheckList, prevCheckListItem: CheckListItem,
        updatedCheckListItem: DTOCheckListItem
    ) {
        Log.i("UPDATECHECKLISTITEM", "${updatedCheckListItem.checked}")

        _uiState.update { currentState ->
            val tempCheckLists = currentState.checkLists
            val targetCheckList =
                tempCheckLists.firstOrNull { it.checkListId == checkList.checkListId }
            val targetItem: CheckListItem?
            if (targetCheckList != null) {

                targetItem =
                    targetCheckList.checkListItems.find {
                        it.checkListItemId == prevCheckListItem
                            .checkListItemId
                    }
                targetItem?.name = updatedCheckListItem.name
                targetItem?.checked = updatedCheckListItem.checked

            }
            currentState.copy(checkLists = tempCheckLists)
        }
    }

    fun addCheckListItem(checkList: CheckList, checkListItem: DTOCheckListItem) {
        _uiState.update { currentState ->
            val tempList = currentState.checkLists
            val targetCheckList = tempList.firstOrNull { it.checkListId == checkList.checkListId }

            targetCheckList?.checkListItems?.add(
                CheckListItem(
                    currentState.checkListItemIdValue,
                    checkListItem.name, checkListItem.checked
                )
            )

            currentState.copy(
                checkLists = tempList,
                checkListItemCount = currentState.checkListItemCount + 1,
                checkListItemIdValue = currentState.checkListIdValue + 1,
            )
        }
    }

    fun deleteCheckListItem(checkList: CheckList, checkListItem: CheckListItem) {
        _uiState.update { currentState ->

            val tempList = currentState.checkLists
            val targetCheckList = tempList.firstOrNull { it == checkList }

            targetCheckList?.checkListItems?.remove(checkListItem)

            currentState.copy(
                checkLists = tempList,
                checkListItemCount = currentState.checkListItemCount - 1
            )
        }
    }

    fun checkAllCheckListItems(checkList: CheckList) {
        _uiState.update { currentState ->
            val tempList = currentState.checkLists
            val targetList = tempList.firstOrNull { it.checkListId == checkList.checkListId }

            targetList?.checkListItems?.forEach { it.checked = true}

            currentState.copy()

        }
    }


}