package ru.ageev.nanopost.ui.auth_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.ageev.nanopost.R
import ru.ageev.nanopost.data.remote.model.response.CheckUsernameEnumResponse
import ru.ageev.nanopost.databinding.FragmentAuthorizationBinding
import ru.ageev.nanopost.ui.insets.Inset
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_authorization) {
    private val binding by viewBinding(FragmentAuthorizationBinding::bind)
    private val authViewModel by viewModels<AuthViewModel>()

    @Inject
    lateinit var responseCodeLiveData: MutableLiveData<Int>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainer)

        Inset.setInsets(binding.root)

        var resultEnum = ResultEnum.ToCheckUsername


        with(binding) {
            buttonContinue.setOnClickListener {
                when (resultEnum) {
                    ResultEnum.ToCheckUsername -> {
                        val username = binding.editTextUsername.text.toString()

                        if (authViewModel.checkUsername(username)) {
                            with(textInputLayoutTextUsername) {
                                error = null
                                setErrorIconDrawable(R.drawable.ic_cancel)
                            }

                            authViewModel.checkUsername(username)
                            textInputLayoutTextPassword.visibility = View.VISIBLE
                        } else {
                            with(textInputLayoutTextUsername) {
                                error = getString(R.string.incorrectUsernameLength)
                                setErrorIconDrawable(R.drawable.ic_warning)
                            }
                        }
                    }

                    ResultEnum.ToRegister -> {
                        val password = editTextPassword.text.toString()

                        if (authViewModel.checkPassword(password)) {
                            textInputLayoutTextPassword.error = null
                            resultEnum = ResultEnum.ToPasswordConfirm
                        } else {
                            textInputLayoutTextPassword.error =
                                context?.getString(R.string.password_warning)
                            resultEnum = ResultEnum.ToCheckUsername

                        }
                    }

                    ResultEnum.ToPasswordConfirm -> {
                        if (editTextPassword.text.toString() == editTextPasswordConfirm.text.toString()) {

                            with(textInputLayoutTextPasswordConfirm) {
                                error = null
                            }

                            authViewModel.register(
                                editTextUsername.text.toString(),
                                editTextPasswordConfirm.text.toString()
                            )
                        } else {
                            with(textInputLayoutTextPasswordConfirm) {
                                error = context.getString(R.string.passwords_must_coincide)
                            }
                        }
                    }

                    ResultEnum.ToLogin -> {
                        responseCodeLiveData.observe(viewLifecycleOwner) { response ->
                            authViewModel.login(
                                editTextUsername.text.toString(),
                                editTextPassword.text.toString()
                            )

                            if (response == 400) {
                                with(textInputLayoutTextPassword) {
                                    error = context.getString(R.string.incorrect_password)
                                }
                            } else {
                                with(textInputLayoutTextPassword) {
                                    error = null
                                }
                            }
                        }
                    }
                }
            }


            authViewModel.checkUsernameLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {

                    CheckUsernameEnumResponse.Taken -> {
                        resultEnum = ResultEnum.ToLogin
                        binding.textInputLayoutTextPasswordConfirm.visibility = View.GONE
                    }

                    CheckUsernameEnumResponse.Free -> {
                        binding.textInputLayoutTextPasswordConfirm.visibility = View.VISIBLE
                        resultEnum = ResultEnum.ToRegister
                    }

                    else -> {}
                }
            }
        }

        authViewModel.registerLiveData.observe(viewLifecycleOwner) {
            navController.navigate(R.id.toNavGraph)
        }


        authViewModel.loginLiveData.observe(viewLifecycleOwner) {
            navController.navigate(R.id.toNavGraph)
        }
    }

    enum class ResultEnum {
        ToCheckUsername,
        ToPasswordConfirm,
        ToRegister,
        ToLogin,
    }
}
