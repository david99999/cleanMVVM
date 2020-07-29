package com.demo.clean.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.demo.clean.domain.models.UserProfile
import com.demo.clean.usecases.GetUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserProfileViewModel(private val useCase: GetUserInfoUseCase) : ViewModel() {
    var userProfile = MutableLiveData<UserProfile>()

    fun getUserProfile(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userProfile.postValue(useCase.GetUserProfile(userId))
            }
        }
    }
}

class UserProfileViewModelFactory(private val userInfoUseCase: GetUserInfoUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        UserProfileViewModel(userInfoUseCase) as T
}