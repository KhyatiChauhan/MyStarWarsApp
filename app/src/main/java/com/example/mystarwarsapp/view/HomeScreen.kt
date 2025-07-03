package com.example.mystarwarsapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mystarwarsapp.R
import com.example.mystarwarsapp.viewmodel.MainViewModel

@Composable
fun HomeScreen(onCategorySubmit: (String) -> Unit) {
    var userInput by remember { mutableStateOf("") }
    var isInputValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Star Wars",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userInput,
            onValueChange = {
                userInput = it
                isInputValid = true
            },
            isError = !isInputValid,
            label = { Text("Enter a category: vehicles, films, starships") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        if (!isInputValid){
            Text("Please enter a valid category",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                val trimmed = userInput.trim().lowercase()
                if (trimmed in listOf("starships", "films", "vehicles")) {
                    onCategorySubmit(trimmed)
                } else {
                    isInputValid = false
                }
            }
            //modifier = Modifier.padding(top = 12.dp).align(Alignment.End)
        ) {
            Text("View Results")
        }
    }
}

@Composable
fun ResultList(items: List<Map<String, Any>>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(items) { item ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        item.entries.forEachIndexed { index, (key, value) ->
                            Text(
                                text = "${key.replaceFirstChar { it.uppercase() }}: $value",
                                style = if (index == 0)
                                    MaterialTheme.typography.titleMedium
                                else
                                MaterialTheme.typography.bodyMedium,
                                color = if (index == 0)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_connection_error), contentDescription = "" )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
    }
}