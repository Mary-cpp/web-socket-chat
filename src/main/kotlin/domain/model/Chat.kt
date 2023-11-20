package domain.model

data class Chat(
    val name: String,
    val members: List<Profile>
)
