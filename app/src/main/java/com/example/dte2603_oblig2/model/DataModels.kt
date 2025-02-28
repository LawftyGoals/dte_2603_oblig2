data class MyCheckList(
    val name: String,
    val myCheckListElements: MutableList<MyCheckListElement>
)

data class MyCheckListElement(
    val text: String,
    var checked: Boolean
)

class DataSource {
    fun loadDemoCheckLists(): MutableList<MyCheckList> {
        return mutableListOf(
            MyCheckList("Min todo-liste", mutableListOf(
                MyCheckListElement("Kjøp melk", false),
                // ... add more elements
            )),
            // ... add more lists
        )
    }

    //fun generateRandomList(): MyCheckList {
        // Implement random list generation
    //}
}