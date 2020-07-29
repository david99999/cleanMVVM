package com.demo.clean.framework.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.clean.R
import com.demo.clean.data.di.ServiceLocator
import com.demo.clean.domain.models.UserShortInfo
import com.demo.clean.presentation.UsersListViewModel
import com.demo.clean.presentation.UsersListViewModelFactory
import com.demo.clean.usecases.GetUsersUseCase
import kotlinx.android.synthetic.main.fragment_users.view.*

class UsersFragment : Fragment() {
    private lateinit var content: View
    private lateinit var adapter: UsersAdapter
    private val viewModel: UsersListViewModel by viewModels {
        UsersListViewModelFactory(GetUsersUseCase(ServiceLocator.getRepository()))
    }

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

    private fun goToUserProfile(userInfo: UserShortInfo) {
        val action = UsersFragmentDirections.actionUsersFragmentToUserProfileFragment(userInfo)
        Navigation.findNavController(content)
            .navigate(R.id.action_usersFragment_to_userProfileFragment, action.arguments)
    }
}