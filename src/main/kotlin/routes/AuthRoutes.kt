package routes

import domain.Profile
import domain.ProfileController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(userController: ProfileController) {
    route("/user") {
        post("/reg") {
            val entity = call.receive<Profile>()
            call.respond(HttpStatusCode.Created, "Id: ${userController.registerUser(entity)}")
        }
        post {  }
    }
}