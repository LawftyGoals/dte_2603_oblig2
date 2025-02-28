package com.example.dte2603_oblig2.data

import androidx.annotation.DrawableRes

data class CheckList(
    val checkListId: Int,
    var name: String,
    @DrawableRes var icon: Int,
    var checkListItems: MutableList<CheckListItem>
)

data class DTOCheckList(
    var name: String, @DrawableRes var icon: Int, var checkListItems:
    MutableList<CheckListItem>
)

data class CheckListItem(val checkListItemId: Int, var name: String, var checked: Boolean)

data class DTOCheckListItem(var name: String, var checked: Boolean)
