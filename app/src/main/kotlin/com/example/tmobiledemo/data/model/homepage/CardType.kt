package com.example.tmobiledemo.data.model.homepage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CardType {
    @SerialName("text") TEXT,
    @SerialName("title_description") TITLE_DESC,
    @SerialName("image_title_description") IMAGE_TITLE_DESC
}