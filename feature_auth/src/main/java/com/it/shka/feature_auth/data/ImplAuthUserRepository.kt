package com.it.shka.feature_auth.data

import com.it.shka.courseapp.APIService
import com.it.shka.feature_auth.domain.AuthUserRepository
import javax.inject.Inject

class ImplAuthUserRepository @Inject constructor(private val apiService: APIService ): AuthUserRepository {
    override fun setAuthUser() {
        TODO("Not yet implemented")
    }

    override fun getAuthUser() {
        TODO("Not yet implemented")
    }


}