package com.demo.clean.presentation

import android.util.Log
import androidx.lifecycle.*
import com.demo.clean.data.datasources.remote.RemoteUsersApi
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.usecases.GetUsersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class UsersListViewModel(private val usersUseCase: GetUsersUseCase) : ViewModel() {

    var users = MutableLiveData<List<UserShortInfo>>()
    val subscriptions = CompositeDisposable()

    fun fetchUsersList() {
        subscriptions.add(
            usersUseCase.getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.i("UsersListViewModel", "showing users into recyclerview")
                        users.postValue(it)
                    },
                    {
                        Log.e("error", it.message ?: "Error")
                    })
        )
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}

class UsersListViewModelFactory(private val usersUseCase: GetUsersUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        UsersListViewModel(usersUseCase) as T
}