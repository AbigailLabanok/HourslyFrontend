package com.example.sample_frontend.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_frontend.model.ApiService
import com.example.sample_frontend.ui.data.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    var courses = mutableStateListOf<Course>()
        private set

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                val response = apiService.getCourses()
                courses.clear()
                courses.addAll(response.courses)
            } catch (e: Exception) {
                println("Error loading courses: $e")
            }
        }
    }
}