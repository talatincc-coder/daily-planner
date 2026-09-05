package com.hiva.dailyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyPlannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlannerScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen() {
    var tasks by remember { mutableStateOf(listOf("خرید نقشه و گزارش کارشناسی", "تنظیم صورتجلسه ژئوترسیم", "برنامه‌ریزی بازدید میدانی")) }
    var newTaskText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("دفتر برنامه‌ریزی روزانه") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (newTaskText.isNotBlank()) {
                    tasks = tasks + newTaskText
                    newTaskText = ""
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "افزودن وظیفه")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = newTaskText,
                onValueChange = { newTaskText = it },
                label = { Text("وظیفه یا فعالیت جدید...") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn {
                items(tasks) { task ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = task,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DailyPlannerTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}
