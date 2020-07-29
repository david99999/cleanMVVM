package com.demo.clean.presentation

import androidx.lifecycle.*
import com.demo.clean.data.datasources.remote.RemoteUsersApi
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.usecases.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class UsersListViewModel(private val usersUseCase: GetUsersUseCase) : ViewModel() {

    var users = MutableLiveData<List<UserShortInfo>>()

    fun fetchUsersList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                users.postValue(usersUseCase.getUsersList())
            }
        }
    }
}

class UsersListViewModelFactory(private val usersUseCase: GetUsersUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        UsersListViewModel(usersUseCase) as T
}