package com.veskoiliev.codewars.ui.challengelist.authored

import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.Resource
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import javax.inject.Inject

class AuthoredChallengesScreenBinder @Inject constructor(
        private val view: AuthoredChallengesView
) {
    fun bindChallengesResource(resource: Resource<List<AuthoredChallenge>>?) {
        when (resource) {
            is Resource.LoadingResource -> {
                view.toggleLoading(true)
            }
            is Resource.ErrorResource -> {
                view.toggleLoading(false)
                view.displayError(resource.errorMessage)
            }
            is Resource.SuccessResource -> {
                view.toggleLoading(false)

                val challenges = resource.data!!
                if (challenges.isEmpty()) {
                    view.toggleEmptyView(true)
                } else {
                    view.toggleEmptyView(false)
                    view.showChallengesList(challenges)
                }
            }
            else -> {
                view.toggleLoading(false)
                view.displayError(R.string.error_generic_message)
            }
        }
    }
}