    package com.example.sample_frontend.viewmodel

    import androidx.compose.runtime.MutableState
    import androidx.compose.runtime.mutableStateListOf
    import androidx.compose.runtime.mutableStateOf
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.sample_frontend.model.repositories.CourseRepository
    import com.example.sample_frontend.model.repositories.OfficeHourRepository
    import com.example.sample_frontend.model.repositories.UserRepository
    import com.example.sample_frontend.ui.data.CourseOfficeHour
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
        val isFavorited: MutableState<Boolean> = mutableStateOf(false)
    )

    @HiltViewModel
    class CourseViewModel @Inject constructor(
        private val courseRepository: CourseRepository,
        private val officeHourRepository: OfficeHourRepository,
        private val userRepository: UserRepository
    ) : ViewModel() {

        var courses = mutableStateListOf<CourseUI>()
            private set

        private val _uiState = MutableStateFlow(CourseUiState())
        val uiState = _uiState.asStateFlow()

        private val _searchQuery = MutableStateFlow("")
        val searchQuery = _searchQuery.asStateFlow()

        private val _filteredCourses = MutableStateFlow<List<CourseUI>>(emptyList())
        val filteredCourses = _filteredCourses.asStateFlow()

        private val _favoriteCourses = MutableStateFlow<List<CourseUI>>(emptyList())
        val favoriteCourses = _favoriteCourses.asStateFlow()

        private val _savedOfficeHours = MutableStateFlow<List<CourseOfficeHour>>(emptyList())
        val savedOfficeHours = _savedOfficeHours.asStateFlow()


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
                courses[index].isFavorited.value = !courses[index].isFavorited.value
                _filteredCourses.value = courses.toList()
                _favoriteCourses.value = courses.filter { it.isFavorited.value }
            }
        }

        fun onClickSaveOfficeHour(officehourid: Int) {
            viewModelScope.launch {

                val courseUI = courses.find { course ->
                    course.course.officeHours.any { it.id == officehourid }
                } ?: return@launch

                val officeHour = courseUI.course.officeHours.first { it.id == officehourid }

                officeHour.isSaved.value = !officeHour.isSaved.value

                val allSaved = courses.flatMap { it.course.officeHours }
                    .filter { it.isSaved.value }

                _savedOfficeHours.value = allSaved
                _filteredCourses.value = courses.toList()
            }
        }

        fun loadUserCourses(userid: Int?) {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true, error = null) }
                val result = courseRepository.getUserCourses(userid)
                result.onSuccess { courseResponse ->
                    val courseList = courseResponse.map { courseResponse ->
                        val officeHours = courseResponse.officeHours.map { oh ->
                            oh.copy(isSaved = mutableStateOf(oh.isSaved.value))
                        }
                        val newCourse = courseResponse.copy(officeHours = officeHours)

                        val isFavorited = courses.find { it.course.id == newCourse.id }?.isFavorited ?: mutableStateOf(false)
                        CourseUI(course = newCourse, isFavorited = isFavorited)
                    }
                    val allSavedOfficeHours = courseList.flatMap { it.course.officeHours }.filter { it.isSaved.value }
                    _savedOfficeHours.value = allSavedOfficeHours
                    _favoriteCourses.value = courseList.filter { it.isFavorited.value }
                    _uiState.update { it.copy(isLoading = false, courses = courseResponse) }
                }.onFailure { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message) }
                }
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