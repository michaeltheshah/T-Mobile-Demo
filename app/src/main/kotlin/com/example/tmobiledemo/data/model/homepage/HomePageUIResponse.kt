package com.example.tmobiledemo.data.model.homepage

import androidx.compose.ui.graphics.Color
import com.example.tmobiledemo.util.extensions.color
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class HomePageUIResponse(
    @SerialName("page")
    val page: Page? = null
)

@Serializable
data class Page(
    @SerialName("cards")
    val cards: List<PageCard> = listOf()
)

@Serializable
data class PageCard(
    @SerialName("card_type") val cardType: CardType,
    val card: Card
)

/** Ideally, these cards would be of individual sealed class types. I would create custom
 *  serializers for each card_type, but in the interest of time, I used this generic Card.
 */

@Serializable
data class Card(
    val value: String? = null,
    val attributes: Attributes? = null,
    val title: TitleDescription? = null,
    val description: TitleDescription? = null,
    val image: Image? = null
)

@Serializable
data class Attributes(
    @SerialName("font")
    val font: Font? = null,
    @SerialName("text_color")
    val textColor: String? = null
)

@Serializable
data class TitleDescription(
    @SerialName("attributes")
    val attributes: Attributes? = null,
    @SerialName("value")
    val value: String? = null
) {
    val fontSize: Int
        get() = attributes?.font?.size ?: 12

    val fontColor: Color
        get() = attributes?.textColor?.color ?: Color.Black
}

@Serializable
data class Image(
    @SerialName("size")
    val size: Size? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Font(
    @SerialName("size")
    val size: Int? = null
)

@Serializable
data class Size(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("width")
    val width: Int? = null
)