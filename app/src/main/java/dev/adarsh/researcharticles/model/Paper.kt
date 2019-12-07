package dev.adarsh.researcharticles.model

import com.google.gson.annotations.SerializedName


data class Paper(val response: Response) {
    data class Response(val docs: List<Doc>,val maxScore: Double,val numFound: Int,val start: Int) {
        data class Doc(
            val abstract: List<String>,
            @SerializedName("article_type")
            val articleType: String,
            @SerializedName("author_display")
            val authorDisplay: List<String>,
            val eissn: String,
            val id: String,
            val journal: String,
            @SerializedName("publication_date")
            val publicationDate: String,
            val score: Double,
            @SerializedName("title_display")
            val titleDisplay: String
        )
    }
}