package com.veskoiliev.codewars.ui.challengelist.authored

import android.support.annotation.StringRes
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge

interface AuthoredChallengesView {
    fun toggleLoading(visible: Boolean)
    fun toggleEmptyView(visible: Boolean)
    fun displayError(@StringRes errorMessage: Int)
    fun showChallengesList(challenges: List<AuthoredChallenge>)
}