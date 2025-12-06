package com.example.sample_frontend.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_frontend.model.ApiService
import com.example.sample_frontend.ui.data.TeacherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    var teachers = mutableStateListOf<TeacherInfo>()
        private set

    init {
        loadTeachersFromBackend()
    }

    private fun loadTeachersFromBackend() {
        viewModelScope.launch {
            try {
                val response = apiService.getCourses()

                val teacherMap = LinkedHashMap<String, TeacherInfo>()

                response.courses.forEach { c ->
                    // use user_id as id
                    val teacherId = c.user_id.toString()
                    val netid = c.instructor
                    val name = netid // use netid first
                    val className =
                        "${c.subject} ${c.course_number}" // use class number as className
                    val location = "" // didn't provide
                    val times = emptyList<String>() // didn't provide
                    val isTeacher = true // assume is all teachers

                    // avoid repetition
                    teacherMap[teacherId] = TeacherInfo(
                        id = teacherId,
                        name = name,
                        className = className,
                        location = location,
                        times = times,
                        isTeacher = isTeacher,
                        isFavorited = false
                    )
                }

                teachers.clear()
                teachers.addAll(teacherMap.values)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onClickFavorite(id: String) {
        val index = teachers.indexOfFirst { it.id == id }
        if (index != -1) {
            val teacher = teachers[index]
            teachers[index] = teacher.copy(isFavorited = !teacher.isFavorited)
        }
    }
}
