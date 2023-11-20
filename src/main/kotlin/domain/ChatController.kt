package domain

import domain.model.Chat
import domain.model.Profile

interface ChatController {
    fun createNewChat() : Chat
    fun removeParticipant()
    fun addParticipants(list: List<Profile>)
    fun deleteChat()
}