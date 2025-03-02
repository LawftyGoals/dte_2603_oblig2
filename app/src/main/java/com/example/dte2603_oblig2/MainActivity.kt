package com.example.dte2603_oblig2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dte2603_oblig2.ui.checklist.CheckListScreen
import com.example.dte2603_oblig2.ui.theme.Dte2603_oblig2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Optional: make status/navigation bars edge-to-edge
        enableEdgeToEdge()

        setContent {
            Dte2603_oblig2Theme {
                CheckListScreen()
            }
        }
    }
}
