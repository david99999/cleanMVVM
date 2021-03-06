package com.demo.clean.framework.screens.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.demo.clean.R
import com.demo.clean.framework.sharedpreferences.PreferencesManager
import com.demo.clean.presentation.UserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_profile.*
import javax.inject.Inject

@AndroidEntryPoint
class UserProfileFragment : Fragment() {
    private val viewModel: UserProfileViewModel by viewModels()

    private val args: UserProfileFragmentArgs by navArgs()

    @Inject
    lateinit var preferences: PreferencesManager

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
            preferences.setName(profile.name)
        })
        viewModel.getUserProfile(args.userInfo.id)
    }
}