package com.group1.cst438_01_project_02_group_01_app.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.group1.cst438_01_project_02_group_01_app.data.model.TimeSlotDto
import com.group1.cst438_01_project_02_group_01_app.data.repository.TimeSlotRepository
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val timeSlots: List<TimeSlotDto>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

class HomeViewModel(
    private val timeSlotRepository: TimeSlotRepository
) : ViewModel() {
    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Loading)
        private set

    init {
        loadTimeSlots()
    }

    fun loadTimeSlots() {
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            uiState = try {
                HomeUiState.Success(timeSlotRepository.getTimeSlots())
            } catch (exception: Exception) {
                HomeUiState.Error(exception.message ?: "Unable to load time slots")
            }
        }
    }

    companion object {
        fun factory(repository: TimeSlotRepository = TimeSlotRepository()): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                        return HomeViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
    }
}
