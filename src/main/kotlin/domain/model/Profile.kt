package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String,
    val surname: String,
    val login: String,
    val password: String
)
