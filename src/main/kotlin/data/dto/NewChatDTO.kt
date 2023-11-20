package data.dto

/**
 * DTO with the information from the client when the chat should be created
 *
 * @param members holds information of chat participants
 */
data class NewChatDTO(
    val chatName: String,
    val members: List<ProfileClientInternalDTO>
)
