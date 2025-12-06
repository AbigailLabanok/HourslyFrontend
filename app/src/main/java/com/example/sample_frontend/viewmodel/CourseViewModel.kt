package com.example.sample_frontend.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_frontend.model.ApiService
import com.example.sample_frontend.model.repositories.CourseRepository
import com.example.sample_frontend.ui.data.Course
import com.example.sample_frontend.ui.data.CourseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CourseUiState(
    val isLoading: Boolean = false,
    val courses: List<CourseResponse> = emptyList(),
    val error: String? = null
)

data class CourseUI(
    val course: CourseResponse,
    val isFavorited: Boolean = false
)

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    var courses = mutableStateListOf<CourseUI>()
        private set

    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _filteredCourses = MutableStateFlow<List<CourseUI>>(emptyList())
    val filteredCourses = _filteredCourses.asStateFlow()


    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = courseRepository.getAllCourses()
            result.onSuccess { course ->
                courses.clear()
                courses.addAll(course.map { CourseUI(it) })
                _filteredCourses.value = courses.toList()
                _uiState.update { it.copy(isLoading = false, courses = course) }
            }.onFailure { exception ->
                _uiState.update { it.copy(isLoading = false, error = exception.message) }
            }
        }
    }

    fun onClickFavorite(id: Int) {
        val index = courses.indexOfFirst { it.course.id == id }
        if (index != -1) {
            val course = courses[index]
            courses[index] = course.copy(isFavorited = !course.isFavorited)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        val filtered = if (query.isBlank()) {
            courses.toList()
        } else   {
            courses.filter {
                it.course.name.contains(query, ignoreCase = true) ||
                        it.course.code.contains(query, ignoreCase = true)
            }
        }
        _filteredCourses.value = filtered
    }

    suspend fun getCourseById(id: Int): CourseResponse? {
        return courses.find { it.course.id == id }?.course ?:
        courseRepository.getCourse(id).getOrNull()
    }
}