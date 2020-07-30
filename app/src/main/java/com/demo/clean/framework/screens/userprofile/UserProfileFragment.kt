package com.demo.clean.framework.screens.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.demo.clean.R
import com.demo.clean.data.di.ServiceLocator
import com.demo.clean.data.mappers.UserDetailedInfoMapper
import com.demo.clean.data.mappers.UserShortInfoMapper
import com.demo.clean.framework.room.UsersDatabase
import com.demo.clean.presentation.UserProfileViewModel
import com.demo.clean.presentation.UserProfileViewModelFactory
import com.demo.clean.usecases.GetUserInfoUseCase
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : Fragment() {
    private val viewModel: UserProfileViewModel by viewModels {
        UserProfileViewModelFactory(
            GetUserInfoUseCase(
                ServiceLocator.getRepository(
                    UsersDatabase.getUsersDao(
                        requireActivity().application
                    ),
                    UserDetailedInfoMapper(),
                    UserShortInfoMapper()
                )
            )
        )
    }

    private val args: UserProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userProfile.observe(viewLifecycleOwner, Observer { profile ->
            userProfileName.text = profile.name
            userProfileCompany.text = profile.company
            userProfileAddress.text = profile.address
        })
        viewModel.getUserProfile(args.userInfo.id)
    }
}