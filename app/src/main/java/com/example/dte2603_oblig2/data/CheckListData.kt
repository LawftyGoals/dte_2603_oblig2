package com.example.dte2603_oblig2.data

import androidx.annotation.DrawableRes

data class CheckList(
    val checkListId: Int,
    val name: String,
    @DrawableRes var icon: Int,
    val checkListItems: MutableList<CheckListItem>
)

data class CheckListItem(val checkListItemId: Int, val name: String, val checked: Boolean)

data class DTOCheckListItem(val name: String, val checked: Boolean)
