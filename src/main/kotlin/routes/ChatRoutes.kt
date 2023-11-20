package routes

import data.dto.NewChatDTO
import domain.ChatController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*

fun Route.chatRoutes(
    controller: ChatController
){
    route("/chat"){
        authenticate {
            post("/create") {
                val requestBody = call.receive<NewChatDTO>()
                controller.createNewChat().also {
                    controller.addParticipants(it.members)
                }
                // redirect to chat

            }
            webSocket("/{id}") {

            }
        }
    }
}