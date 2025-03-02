package com.example.dte2603_oblig2.data

import com.example.dte2603_oblig2.R

data class UiState(
    val isMultiColumn: Boolean = false,
    val checkLists: MutableList<CheckList> = mutableListOf(
        CheckList(
            checkListId = 0,
            "Red", R.drawable.pokemon_trainer_red,
            mutableListOf(
                CheckListItem(0, "Bulbasaur", false),
                CheckListItem(1, "Charmander", true),
                CheckListItem(2, "Squirtle", false)
            )
        ),
        CheckList(
            checkListId = 1,
            "Blue",
            R.drawable.pokemon_trainer_blue,
            mutableListOf(
                CheckListItem(3, "Bulbasaur", false),
                CheckListItem(4, "Charmander", false),
                CheckListItem(5, "Squirtle", true)
            )
        ),
        CheckList(
            checkListId = 2,
            "Min todo-liste",
            R.drawable.ic_launcher_background,
            mutableListOf(
                CheckListItem(6, "Skriv søknad", false), CheckListItem(
                    7, "Send søknad",
                    false
                ), CheckListItem(8, "Få jobb", false), CheckListItem(9, "Jobb hardt", true)
            )
        ),
        CheckList(
            checkListId = 3,
            "Husvask",
            R.drawable.ic_launcher_background, mutableListOf(
                CheckListItem(10, "Kjøkkenet", true),
                CheckListItem(11, "Badet", false), CheckListItem(12, "Stua", false), CheckListItem
                    (13, "Soverom", true)
            )
        ),
        CheckList(
            checkListId = 4,
            "Studieplan",
            R.drawable.ic_launcher_background,
            mutableListOf(
                CheckListItem(14, "Gjør matteoppgaver", false), CheckListItem(
                    15, "Gjør fysikkoppgaver", true
                ), CheckListItem(16, "Gjør kjemioppgaver", false)
            )
        )
    ),
    val checkListCount: Int = checkLists.count(),
    val checkListIdValue: Int = checkLists.count(),

    val checkListItemCount: Int = 16,
    val checkListItemIdValue: Int = 17,

    val checkListNames: List<String> = listOf(
        "Vask", "Yellow", "Flybilletter", "Konsert",
        "Pikachu", "Random"
    ),

    val checkListItemsRandom: List<DTOCheckListItem> = listOf(
        DTOCheckListItem("Tackle", false),
        DTOCheckListItem("Sjekke Flyselskap", false),
        DTOCheckListItem("Charmander", true),
        DTOCheckListItem("Bulbasaur", true),
        DTOCheckListItem("Sjekke Hotell", false),
        DTOCheckListItem("Sjekke Transport", false),
        DTOCheckListItem("Ordne Transport", true),
        DTOCheckListItem("Thunderbolt", false),
        DTOCheckListItem("Ordne Retur", true),
        DTOCheckListItem("Avklare fri fra jobb", true)
    ),

    val iconsList: List<Int> = listOf(
        R.drawable.ic_launcher_background, R.drawable
            .pokemon_trainer_red, R.drawable.pokemon_trainer_blue, R.drawable.ic_launcher_foreground
    )
)