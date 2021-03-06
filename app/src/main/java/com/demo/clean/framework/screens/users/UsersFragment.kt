package com.demo.clean.framework.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.clean.R
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.framework.sharedpreferences.PreferencesManager
import com.demo.clean.presentation.UsersListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_users.view.*
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private lateinit var content: View
    private lateinit var adapter: UsersAdapter
    private val viewModel: UsersListViewModel by viewModels()

    @Inject
    lateinit var preferences: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        content = inflater.inflate(R.layout.fragment_users, container, false)
        adapter = UsersAdapter(::goToUserProfile)
        content.usersRecycler.adapter = adapter
        content.usersRecycler.layoutManager = LinearLayoutManager(requireActivity())
        return content
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.users.observe(viewLifecycleOwner, Observer { users -> adapter.users = users })
        viewModel.fetchUsersList()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(requireContext(), preferences.getName(), Toast.LENGTH_LONG).show()
    }

    private fun goToUserProfile(userInfo: UserShortInfo) {
        val action = UsersFragmentDirections.actionUsersFragmentToUserProfileFragment(userInfo)
        Navigation.findNavController(content)
            .navigate(R.id.action_usersFragment_to_userProfileFragment, action.arguments)
    }
}