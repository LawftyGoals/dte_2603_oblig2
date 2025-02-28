package com.example.dte2603_oblig2.ui.checklist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.elevatedCardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dte2603_oblig2.R
import com.example.dte2603_oblig2.data.CheckList
import com.example.dte2603_oblig2.data.CheckListItem
import com.example.dte2603_oblig2.data.DTOCheckList
import com.example.dte2603_oblig2.data.DTOCheckListItem
import com.example.dte2603_oblig2.ui.theme.Dte2603_oblig2Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckListScreen(checkListViewModel: CheckListViewModel = viewModel()) {

    val checkListUiState by checkListViewModel.uiState.collectAsState()
    val checkLists = checkListUiState.checkLists
    val checkListCount = checkListUiState.checkListCount

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // A simple top app bar
            TopAppBar(
                title = { Text("MineHuskelister") },
                actions = {
                    // Toggle between 2 columns and 1 column
                    Text("Vis som 2 kolonner")
                    Spacer(Modifier.padding(4.dp))
                    Switch(checked = checkListUiState.isMultiColumn, onCheckedChange = {
                        checkListViewModel
                            .toggleCheckListColumns()
                    })
                }
            )
        },
        bottomBar = {
            // A simple footer showing total list count
            Footer(totalLists = checkListCount)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            // Button for creating a new checkList
            Button(
                onClick = {
                    checkListViewModel.addCheckList(
                        DTOCheckList(
                            "Navn", R.drawable
                                .ic_launcher_background, mutableListOf()
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Legg til ny sjekkliste")
            }

            // Lazy grid for checkLists
            LazyVerticalGrid(
                columns = GridCells.Fixed(if (checkListUiState.isMultiColumn) 2 else 1),
                modifier = Modifier.weight(1f), // fill remaining space
                contentPadding = PaddingValues(8.dp)
            ) {
                items(checkLists) { checkList ->
                    ChecklistCard(
                        checkList = checkList,
                        checkListViewModel = checkListViewModel,
                        onDelete = {
                            checkListViewModel.deleteCheckList(checkList)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChecklistCard(
    checkList: CheckList,
    checkListViewModel: CheckListViewModel,
    onDelete: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Title row + expand toggle + delete icon
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = checkList.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Expand/Collapse icon
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (expanded) "Collapse" else "Expand"
                    )
                }

            }

            Column(
                Modifier
                    .border(2.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                // Show tasks only when expanded
                if (expanded || true) {
                    if (checkList.checkListItems.isEmpty()) {
                        Text("Tomt")
                    } else {
                        checkList.checkListItems.forEach { task ->
                            TaskItem(
                                task, checkList, checkListViewModel
                            )
                        }
                    }

                } else {

                    Spacer(Modifier.padding(1.dp))
                }

            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                // Delete checkList icon
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }

                IconButton(onClick = {}) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }

                IconButton(onClick = {}) {
                    Box {
                        Icon(Icons.Default.Check, contentDescription = "Check")
                        Row {
                            Spacer(Modifier.padding(3.dp))
                            Icon(Icons.Default.Check, contentDescription = "Check")
                        }

                    }
                }
            }

        }
    }
}

@Composable
fun TaskItem(task: CheckListItem, checkList: CheckList, checkListViewModel: CheckListViewModel) {
    // For demonstration, we'll track whether the item is "checked"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = Color.LightGray.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(checked = task.checked, onCheckedChange = { checkListViewModel
            .updateCheckListItemState(checkList, task, DTOCheckListItem(task.name, !task.checked)
            ) })
        Spacer(Modifier.padding(8.dp))
        Text(text = task.name)
    }
}

@Composable
fun Footer(totalLists: Int) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Totalt $totalLists lister",
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    Dte2603_oblig2Theme {
        CheckListScreen()
    }
}
