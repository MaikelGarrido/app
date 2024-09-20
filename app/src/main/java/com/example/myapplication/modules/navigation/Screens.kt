package com.example.myapplication.modules.navigation

import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.utils.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
sealed interface Screens {

    @Serializable
    data object CardScreens : Screens {

        @Serializable
        data object CardListScreen : Screens

        @Serializable
        data object AddCard : Screens

        @Serializable
        data class CardDetails(
            val card: Card
        ) : Screens {
            companion object {
                val typeMap = mapOf(typeOf<Card>() to serializableType<Card>(isNullableAllowed = true))
            }
        }
    }

    @Serializable
    data object MapsScreens : Screens {
        @Serializable
        data object MapHome: Screens
    }

}