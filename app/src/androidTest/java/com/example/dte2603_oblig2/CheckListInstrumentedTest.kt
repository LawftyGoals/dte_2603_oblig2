package com.example.dte2603_oblig2


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dte2603_oblig2.ui.checklist.CheckListScreen
import com.example.dte2603_oblig2.ui.theme.Dte2603_oblig2Theme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CheckListInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val addText = "Legg til ny sjekkliste"
    private val total = "Totalt"
    private val totalAddResult = "Totalt 6 lister"


    @Test
    fun update_total_number_of_checklists_after_add() {
        composeTestRule.setContent {
            Dte2603_oblig2Theme {
                CheckListScreen()
            }
        }

        composeTestRule.onNodeWithText(addText).performClick()
        composeTestRule.onNodeWithText(total, substring = true)
            .assertExists(totalAddResult)

    }

    private val deleteRed = "DeleteRed"
    private val ok = "Ok"
    private val totalDeleteResult = "Totalt 4 lister"

    @Test
    fun update_total_number_of_checklists_after_delete() {
        composeTestRule.setContent {
            Dte2603_oblig2Theme {
                CheckListScreen()
            }
        }

        composeTestRule.onNodeWithTag(deleteRed).performClick()
        composeTestRule.onNodeWithText(ok).performClick()
        composeTestRule.onNodeWithText(total, substring = true)
            .assertExists(totalDeleteResult)

    }

    private val tasks = "oppgaver"
    private val resultDeletedTasks = "5 av 14 oppgaver er utført"


    @Test
    fun update_total_number_of_checklistsitems_after_delete() {
        composeTestRule.setContent {
            Dte2603_oblig2Theme {
                CheckListScreen()
            }
        }

        composeTestRule.onNodeWithTag(deleteRed).performClick()
        composeTestRule.onNodeWithText(ok).performClick()
        composeTestRule.onNodeWithText(tasks, substring = true)
            .assertExists(resultDeletedTasks)

    }

    private val checkAllRed = "checkAllRed"
    private val allCheckedRedResult = "8 av 17 oppgaver er utført"

    @Test
    fun update_total_number_of_checklistsitems_after_all_checked() {
        composeTestRule.setContent {
            Dte2603_oblig2Theme {
                CheckListScreen()
            }
        }

        composeTestRule.onNodeWithTag(checkAllRed).performClick()
        composeTestRule.onNodeWithText(tasks, substring = true)
            .assertExists(allCheckedRedResult)

    }
}