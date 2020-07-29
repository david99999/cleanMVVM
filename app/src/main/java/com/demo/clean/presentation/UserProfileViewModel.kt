package com.demo.clean.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.clean.domain.models.UserProfile
import com.demo.clean.usecases.GetUserInfoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserProfileViewModel(private val useCase: GetUserInfoUseCase) : ViewModel() {
    var userProfile = MutableLiveData<UserProfile>()
    val subscriptions = CompositeDisposable()

    fun getUserProfile(userId: Int) {
        subscriptions.add(useCase.GetUserProfile(userId)
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

class UserProfileViewModelFactory(private val userInfoUseCase: GetUserInfoUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        UserProfileViewModel(userInfoUseCase) as T
}