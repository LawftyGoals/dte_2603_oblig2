package com.example.dte2603_oblig2.data

import androidx.annotation.DrawableRes
import com.example.dte2603_oblig2.R

data class CheckList(
    var name: String,
    @DrawableRes var icon: Int,
    var checkListItems: MutableList<CheckListItem>
)

data class CheckListItem(val name: String, val checked: Boolean)

object CheckListSamples {

    val CheckLists = mutableListOf(
        CheckList(
            "Red", R.drawable.pokemon_trainer_blue,
            mutableListOf(
                CheckListItem("Bulbasaur", false),
                CheckListItem("Charmander", true),
                CheckListItem("Squirtle", false)
            )
        ),
        CheckList(
            "Blue",
            R.drawable.pokemon_trainer_blue,
            mutableListOf(
                CheckListItem("Bulbasaur", false),
                CheckListItem("Charmander", false),
                CheckListItem("Squirtle", true)
            )
        )
    )

}