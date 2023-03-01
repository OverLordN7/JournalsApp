package com.example.journalsapp.model

import kotlinx.serialization.SerialName

data class JournalLastIssue(
    val id: Int,
    val name: String,
    @SerialName("has_articles")
    val hasArticles: Boolean,
    @SerialName("amount_of_pages")
    val amountOfPage: Int,
    val year: Int,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("released_at")
    val releaseAt: String,
    val cover: String,
    @SerialName("thumb_mobile")
    val thumbMobile: String,
    @SerialName("cover_mobile")
    val coverMobile: String,
    @SerialName("journal_id")
    val journalId: Int,
    @SerialName("free_issue")
    val freeIssue: Boolean

)