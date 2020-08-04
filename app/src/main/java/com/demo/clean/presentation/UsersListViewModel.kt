package com.demo.clean.presentation

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.usecases.GetUsersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UsersListViewModel @ViewModelInject constructor(private val usersUseCase: GetUsersUseCase) : ViewModel() {

    var users = MutableLiveData<List<UserShortInfo>>()
    private val subscriptions = CompositeDisposable()

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
