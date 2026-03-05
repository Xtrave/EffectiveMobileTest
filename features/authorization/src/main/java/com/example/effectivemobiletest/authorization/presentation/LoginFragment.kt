package com.example.effectivemobiletest.authorization.presentation

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletest.authorization.R
import com.example.effectivemobiletest.authorization.databinding.FragmentLoginBinding
import com.example.effectivemobiletest.authorization.navigation.LoginNavigation
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private val navigation: LoginNavigation by inject()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

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

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

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
        viewModel.isLoginEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.loginButton.isEnabled = isEnabled
            binding.loginButton.backgroundTintList = if (isEnabled) {
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.button_enabled))
            } else {
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.button_disabled))
            }
        }

        viewModel.navigateToMain.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                navigation.toMain(findNavController())
            }
        }
    }

    fun onSocialClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        requireContext().startActivity(intent)
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.onLoginClick()
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