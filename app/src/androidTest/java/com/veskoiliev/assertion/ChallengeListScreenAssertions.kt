package com.veskoiliev.assertion

import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.veskoiliev.assertion.RecyclerViewAssertions.assertRecyclerViewItemsInOrder
import com.veskoiliev.assertion.ViewAssertions.assertDisplayingView
import com.veskoiliev.assertion.ViewAssertions.assertNotDisplayingView
import com.veskoiliev.codewars.R
import com.veskoiliev.codewars.data.local.model.challenge.AuthoredChallenge
import com.veskoiliev.codewars.data.local.model.challenge.CompletedChallenge
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChallengeListScreenAssertions {
    init {
        assertDisplayingView(withId(R.id.challenge_list_bottom_navigation))
    }

    fun withCompletedChallengesTab(): ChallengeListScreenAssertions {
        assertDisplayingView(withId(R.id.challenge_list))
        return this
    }

    fun withErrorLoadingCompletedChallenges(): ChallengeListScreenAssertions {
        assertDisplayingView(withId(R.id.network_state_item_error_msg))
        return this
    }

    fun withRetryButton() {
        assertDisplayingView(withId(R.id.network_state_item_retry_button))
    }

    fun withCompletedChallengesList(): ChallengeListScreenAssertions {
        assertRecyclerViewItemsInOrder(R.id.challenge_list, completedChallengesInOrder(listOf(
                // These values come from Assets.kt
                CompletedChallenge(id = "completedChallengeId1", name = "Completed Challenge 1", completedAt = "completedAtDate1", networkPage = 0),
                CompletedChallenge(id = "completedChallengeId2", name = "Completed Challenge 2", completedAt = "completedAtDate2", networkPage = 0),
                CompletedChallenge(id = "completedChallengeId3", name = "Completed Challenge 3", completedAt = "completedAtDate3", networkPage = 0)
        )))
        return this
    }

    private fun completedChallengesInOrder(challenges: List<CompletedChallenge>): List<Matcher<View>> {
        fun hasName(name: String) =
                hasDescendant(allOf(
                        withId(R.id.completed_challenge_name),
                        withText(name)
                ))

        fun wasCompletedAt(completedAt: String) =
                hasDescendant(allOf(
                        withId(R.id.completed_challenge_completion_date),
                        withText(completedAt)
                ))

        return challenges.map { allOf(hasName(it.name), wasCompletedAt(it.completedAt)) }
    }

    fun withoutRetryButton() {
        assertNotDisplayingView(withId(R.id.network_state_item_retry_button))
    }

    fun withAuthoredChallengesList(): ChallengeListScreenAssertions {
        assertRecyclerViewItemsInOrder(R.id.authored_challenges_list, authoredChallengesInOrder(listOf(
                // These values come from Assets.kt
                AuthoredChallenge(id = "authoredChallengeId1", name = "Authored Challenge 1", description = "Description 1", userName=""),
                AuthoredChallenge(id = "authoredChallengeId2", name = "Authored Challenge 2", description = "Description 2", userName=""),
                AuthoredChallenge(id = "authoredChallengeId3", name = "Authored Challenge 3", description = "Description 3", userName="")
        )))
        return this
    }

    private fun authoredChallengesInOrder(challenges: List<AuthoredChallenge>): List<Matcher<View>> {
        fun hasName(name: String) =
                hasDescendant(allOf(
                        withId(R.id.authored_challenge_name),
                        withText(name)
                ))

        fun hasDescription(name: String) =
                hasDescendant(allOf(
                        withId(R.id.authored_challenge_description),
                        withText(name)
                ))

        return challenges.map { allOf(hasName(it.name), hasDescription(it.description)) }
    }

    fun withErrorLoadingAuthoredChallenges() {
        assertDisplayingView(allOf(withId(R.id.authored_challenges_message_view), withText(R.string.error_fetching_authored_challenges)))
    }
}
