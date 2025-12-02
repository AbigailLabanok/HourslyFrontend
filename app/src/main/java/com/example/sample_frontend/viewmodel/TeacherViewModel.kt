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
class TeacherViewModel @Inject constructor(): ViewModel() {
    var teachers = mutableStateListOf<TeacherInfo>()

    init {
        teachers.addAll(sampleTeachers)
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