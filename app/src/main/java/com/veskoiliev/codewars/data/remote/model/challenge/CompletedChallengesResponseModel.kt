package com.veskoiliev.codewars.data.remote.model.challenge

data class CompletedChallengesResponseModel(
        val totalPages: Int,
        val totalItems: Int,
        val data: List<CompletedChallengeModel>
)