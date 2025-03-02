package com.example.dte2603_oblig2.ui.checklist

import androidx.lifecycle.ViewModel
import com.example.dte2603_oblig2.data.CheckList
import com.example.dte2603_oblig2.data.CheckListItem
import com.example.dte2603_oblig2.data.DTOCheckListItem
import com.example.dte2603_oblig2.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random


class CheckListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun toggleCheckListColumns() {
        _uiState.update { currentState -> currentState.copy(isMultiColumn = !currentState.isMultiColumn) }
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

    fun updateCheckListItemState(
        prevCheckList: CheckList, prevCheckListItem: CheckListItem,
        updatedCheckListItem: DTOCheckListItem
    ) {
        _uiState.update { currentState ->
            val tempCheckLists = currentState.checkLists.map { checkList ->
                if (checkList.checkListId == prevCheckList.checkListId) {
                    CheckList(
                        checkList.checkListId, checkList.name, checkList.icon,
                        checkList.checkListItems.map { checkListItem ->
                            if (checkListItem.checkListItemId == prevCheckListItem.checkListItemId) {
                                CheckListItem(
                                    checkListItem.checkListItemId, updatedCheckListItem
                                        .name, updatedCheckListItem.checked
                                )

                            } else {
                                checkListItem
                            }

                        }.toMutableList()
                    )

                } else {
                    checkList
                }
            }
            currentState.copy(checkLists = tempCheckLists.toMutableList())
        }
    }

    fun checkAllCheckListItems(prevCheckList: CheckList) {
        _uiState.update { currentState ->
            val tempCheckLists = currentState.checkLists.map { checkList ->
                if (checkList.checkListId == prevCheckList.checkListId) {
                    CheckList(
                        checkList.checkListId, checkList.name, checkList.icon,
                        checkList.checkListItems.map { checkListItem ->
                            CheckListItem(
                                checkListItem.checkListItemId, checkListItem
                                    .name, true
                            )

                        }.toMutableList()
                    )

                } else {
                    checkList
                }
            }
            currentState.copy(checkLists = tempCheckLists.toMutableList())

        }
    }

    fun addRandomCheckList() {
        _uiState.update { currentState ->
            val updatedList = currentState.checkLists

            val icon = currentState.iconsList[Random.nextInt(currentState.iconsList.count())]
            val name =
                currentState.checkListNames[Random.nextInt(currentState.checkListNames.count())]

            val checkListItems: MutableList<CheckListItem> = mutableListOf()

            var checkListItemIdValue = currentState.checkListItemIdValue

            for (i in 0..3) {

                val checkListItemDTO = currentState.checkListItemsRandom[Random.nextInt(
                    currentState
                        .checkListItemsRandom.count()
                )]

                checkListItems.add(
                    CheckListItem(
                        checkListItemIdValue, checkListItemDTO.name,
                        checkListItemDTO
                            .checked
                    )
                )
                checkListItemIdValue += 1
            }

            val newCheckList = CheckList(
                currentState.checkListIdValue, name, icon, checkListItems
            )
            updatedList.add(newCheckList)

            currentState.copy(
                checkListIdValue = currentState.checkListIdValue + 1,
                checkListItemIdValue = checkListItemIdValue,
                checkLists = updatedList
            )
        }
    }
}