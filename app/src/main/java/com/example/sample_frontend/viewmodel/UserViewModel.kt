package com.example.sample_frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_frontend.model.repositories.UserRepository
import com.example.sample_frontend.ui.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState(isLoading = false))
    val uiState = _uiState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    fun createUser(name: String, netid: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = userRepository.createUser(name, netid)

            result.onSuccess { response ->
                val user = User(
                    id = response.id.toString(),
                    name = response.name,
                    netid = response.netId
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        users = it.users + user
                    )
                }
                _currentUser.value = user
            }.onFailure { exception ->

                _uiState.update {
                    it.copy(isLoading = false, error = exception.message)
                }
            }
        }
    }

    fun fetchUser(userid: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = userRepository.getUser(userid)

            result.onSuccess { response ->
                val user = User(
                    id = response.id.toString(),
                    name = response.name,
                    netid = response.netId
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        users = it.users + user
                    )
                }
                _currentUser.value = user
            }.onFailure { exception ->
                    _uiState.update {
                    it.copy(isLoading = false, error = exception.message)
                }
            }
        }
    }

//    fun getCurrentUser(): User? {
//        return _uiState.value.users.firstOrNull({ it.id.toInt()== currentUserId })
//    }
}