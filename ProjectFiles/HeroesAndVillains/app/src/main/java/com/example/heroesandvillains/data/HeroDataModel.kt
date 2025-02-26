package com.example.heroesandvillains.data

import com.google.gson.annotations.SerializedName

/**
 * HeroesVillainsDataModel data class to hold the data for heroes and villains
 */
data class HeroDataModel (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("images") val images: Images
)

/**
 * Biography data class to hold the biography details of the heroes and villains
 */
data class Biography (
    @SerializedName("fullName") val fullName: String,
    @SerializedName("alterEgos") val alterEgos: String,
    @SerializedName("aliases") val aliases: List<String>,
    @SerializedName("placeOfBirth") val placeOfBirth: String,
    @SerializedName("firstAppearance") val firstAppearance: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("alignment") val alignment: String
)

/**
 * Images data class to hold the image urls of the heroes and villains
 */
data class Images (
    @SerializedName("xs") val xs: String,
    @SerializedName("sm") val sm: String,
    @SerializedName("md") val md: String,
    @SerializedName("lg") val lg: String
)