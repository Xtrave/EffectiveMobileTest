package com.example.effectivemobiletest.authorization.presentation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletest.authorization.data.LoginState
import com.example.effectivemobiletest.authorization.databinding.FragmentLoginBinding
import com.example.effectivemobiletest.authorization.navigation.LoginNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModel()
    private val navigation: LoginNavigation by inject()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextWatchers()
        setupObservers()
        setupClickListeners()
    }

    private fun setupTextWatchers() {
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEmailChanged(s?.toString() ?: "")
            }
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.onPasswordChanged(s?.toString() ?: "")
            }
        })
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loginButton.isEnabled = state.isLoginEnabled
        }
    }

    fun onSocialClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        requireContext().startActivity(intent)
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            navigation.toMain(findNavController())
        }

        binding.vkButton.setOnClickListener {
            onSocialClick("https://vk.com/")
        }

        binding.okButton.setOnClickListener {
            onSocialClick("https://ok.ru/")
        }

        binding.registerText.isEnabled = false
        binding.forgotPasswordText.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}