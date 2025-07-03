package com.example.mystarwarsapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystarwarsapp.network.SwapiApi
import kotlinx.coroutines.launch

sealed interface CategoryUiState {
    data class Success(val data: List<Map<String, Any>>) : CategoryUiState
    object Error : CategoryUiState
    object Loading : CategoryUiState
    object Idle : CategoryUiState
}

class MainViewModel : ViewModel() {
    var categoryUiState: CategoryUiState by mutableStateOf<CategoryUiState>(CategoryUiState.Idle)
        private set

    fun getCategory(type: String) {
        viewModelScope.launch {
            categoryUiState = CategoryUiState.Loading
            try {
               val categoryResult = SwapiApi.retrofitService.getCategory(type)
               categoryUiState = CategoryUiState.Success(categoryResult)
           } catch (e: Exception) {
               Log.e("MainViewModel", "Error: ${e.localizedMessage}")
               categoryUiState = CategoryUiState.Error
           }
        }
    }

    fun sortResults(by: String) {
        val currentData = (categoryUiState as? CategoryUiState.Success)?.data ?: return
        val sorted = currentData.sortedBy {
            it[by]?.toString()?.lowercase() ?: ""
        }
        categoryUiState = CategoryUiState.Success(sorted)
    }
}