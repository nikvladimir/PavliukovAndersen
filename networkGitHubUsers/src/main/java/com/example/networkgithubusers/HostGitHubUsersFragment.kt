package com.example.networkgithubusers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.networkgithubusers.databinding.FragmentHostGitHubUsersBinding


class HostGitHubUsersFragment : Fragment() {

    private lateinit var binding: FragmentHostGitHubUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHostGitHubUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        parentFragmentManager.beginTransaction().apply {
//            replace(binding.hostGitHubUsersFragment, GitHubUserFragment)
//            commit()
//        }

    }
}