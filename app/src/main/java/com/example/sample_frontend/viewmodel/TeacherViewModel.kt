package com.example.sample_frontend.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_frontend.ui.data.TeacherInfo
import com.example.sample_frontend.ui.data.sampleTeachers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {
    var teachers = mutableStateListOf<TeacherInfo>()
        private set

    init {
        loadTeachersFromBackend()
    }
     private fun loadTeachersFromBackend() {
        viewModelScope.launch {
            try {
                val response = apiService.getCourses()

                // avoid overlap TA / Instructor
                val teacherMap = LinkedHashMap<String, TeacherInfo>()

                response.courses.forEach { c ->
                    c.instructors.forEach { user ->
                        teacherMap[user.id] = TeacherInfo(
                            id = user.id,
                            name = user.name,
                            netid = user.netid,
                            isFavorited = false
                        )
                    }
                    c.tas.forEach { user ->
                        teacherMap[user.id] = TeacherInfo(
                            id = user.id,
                            name = user.name,
                            netid = user.netid,
                            isFavorited = false
                        )
                    }
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
            // Replace the old item with a new copy
            teachers[index] = teacher.copy(isFavorited = !teacher.isFavorited)
        }
    }

}
