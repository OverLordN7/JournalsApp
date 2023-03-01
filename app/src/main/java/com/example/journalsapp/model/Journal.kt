package com.example.journalsapp.model

import kotlinx.serialization.SerialName

data class Journal(
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("category_id")
    val categoryId: Int,
    @SerialName("category_name")
    val categoryName: String,
    val last_issue: JournalLastIssue,
    @SerialName("in_bookmark")
    val inBookmark: Boolean,
    @SerialName("in_favorite")
    val inFavorite: Boolean


)