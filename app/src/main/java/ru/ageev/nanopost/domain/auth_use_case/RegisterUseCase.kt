package ru.ageev.nanopost.domain.auth_use_case

import ru.ageev.nanopost.data.remote.model.RegistrationRequest
import ru.ageev.nanopost.data.remote.model.response.TokenResponse
import ru.ageev.nanopost.data.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend fun execute(registrationRequest: RegistrationRequest) : TokenResponse {
        return registerRepository.register(registrationRequest)
    }
}