package com.example.dte2603_oblig2.data

import com.example.dte2603_oblig2.R

data class UiState(
    val isMultiColumn: Boolean = false,
    val checkLists: MutableList<CheckList> = mutableListOf(
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
        ),
        CheckList(
            "Min todo-liste",
            R.drawable.ic_launcher_background,
            mutableListOf(
                CheckListItem("Skriv søknad", false), CheckListItem(
                    "Send søknad",
                    false
                ), CheckListItem("Få jobb", false), CheckListItem("Jobb hardt", true)
            )
        ),
        CheckList(
            "Husvask",
            R.drawable.ic_launcher_background, mutableListOf(
                CheckListItem("Kjøkkenet", true),
                CheckListItem("Badet", false), CheckListItem("Stua", false), CheckListItem
                    ("Soverom", true)
            )
        ),
        CheckList(
            "Studieplan",
            R.drawable.ic_launcher_background,
            mutableListOf(
                CheckListItem("Gjør matteoppgaver", false), CheckListItem(
                    "Gjør fysikkoppgaver", true
                ), CheckListItem("Gjør kjemioppgaver", false)
            )
        )
    ),
    val checkListCount: Int = checkLists.count()
)