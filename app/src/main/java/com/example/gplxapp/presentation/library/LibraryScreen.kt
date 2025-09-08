package com.example.gplxapp.presentation.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gplxapp.presentation.components.PlaceholderCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Text(
                    text = "Library",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                listOf(
                    "Saved Jobs",
                    "Interview Tips",
                    "Resume Templates",
                    "Career Guides",
                    "Industry Insights",
                    "Skill Assessments",
                    "Salary Information",
                    "Company Reviews"
                )
            ) { item ->
                PlaceholderCard(
                    title = item,
                    backgroundColor = Color(0xFFF0F4FF)
                )
            }
        }
    }
}