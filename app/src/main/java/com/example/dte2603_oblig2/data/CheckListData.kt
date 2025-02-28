package com.example.dte2603_oblig2.data

import androidx.annotation.DrawableRes

data class CheckList(
    var name: String,
    @DrawableRes var icon: Int,
    var checkListItems: MutableList<CheckListItem>
)

data class CheckListItem(val name: String, val checked: Boolean)
