package com.veskoiliev.codewars.data.remote.model

data class RankModel(
        val rank: Long = Long.MIN_VALUE,
        val name: String = "",
        val color: String = "",
        val score: Long = Long.MIN_VALUE
) : Comparable<RankModel> {
    override fun compareTo(other: RankModel): Int {
        return (this.score - other.score).toInt()
    }
}