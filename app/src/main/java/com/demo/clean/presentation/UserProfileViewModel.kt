package com.demo.clean.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.clean.domain.models.UserDetailedInfo
import com.demo.clean.usecases.GetUserInfoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserProfileViewModel @ViewModelInject constructor(private val useCase: GetUserInfoUseCase) :
    ViewModel() {
    var userProfile = MutableLiveData<UserDetailedInfo>()
    val subscriptions = CompositeDisposable()

    fun getUserProfile(userId: Int) {
        subscriptions.add(useCase.getUserProfile(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userProfile.postValue(it) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}
